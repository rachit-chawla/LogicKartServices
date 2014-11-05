package org.gl.project.Services;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.AuthenticationDAO;
import org.gl.project.DAO.UserDAO;
import org.gl.project.EmailServices.WelcomeEmail;
import org.gl.project.Models.User;
import org.gl.project.Models.UserAuthentication;
import org.gl.project.Models.UserAuthenticationCheck;
import org.gl.project.PO.SignUpPO;
import org.gl.project.PO.UpdateUserPO;
import org.gl.project.PO.UserPO;
import org.gl.project.VO.AdminVO;
import org.gl.project.VO.DeleteVO;
import org.gl.project.VO.SignUpVO;
import org.gl.project.VO.UpdateUserVO;
import org.gl.project.VO.UserDetailsVO;
import org.hibernate.exception.ConstraintViolationException;

import com.google.gson.Gson;

@Path("/admin")
public class AdminService 
{
	@POST
	@Path("users/{id}/view")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserById(@PathParam("id") Long id, String data)
	{
		String json = null;
		Gson gson = new Gson();
		UserPO userPO = gson.fromJson(data, UserPO.class);
		String authToken = userPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(authToken);
		UserDetailsVO udv = new UserDetailsVO();
		if(status)
		{
			UserDAO ud = new UserDAO();
			User user = ud.getUserById(id);
			if(user!=null)
			{
				String type = userPO.getType();
				if(type.equals("admin"))
				{
					udv.setId(user.getId());
					udv.setFname(user.getFname());
					udv.setMname(user.getMname());
					udv.setLname(user.getLname());
					udv.setAddress(user.getAddress());
					udv.setDob(user.getDob());
					udv.setEmailId(user.getEmailId());
					udv.setPassword(user.getPassword());
					udv.setGender(user.getGender());
					udv.setPhone(user.getPhone());
					udv.setType(user.getType());
					udv.setStatus(user.getStatus());
					udv.setSuccessMessage("User Details fetched successfully");
				}
				else
				{
					udv.setErrorMessage("You're not authorized to view the details!");
				}
			}
			else
			{
				udv.setErrorMessage("No such user for id: " + id);
			}
		}
		else
		{
			udv.setErrorMessage("Session Expired!");
		}
		json = gson.toJson(udv);
		System.out.println(udv);
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Path("users/{id}/update")
	public Response updateUser(@PathParam("id") Long id, String data)
	{
		Gson gson = new Gson();
		Long userId = id;
		UpdateUserPO updateUserPO = gson.fromJson(data, UpdateUserPO.class);
		String token = updateUserPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		UpdateUserVO updateUserVO = new UpdateUserVO();
		if(status)
		{
			UserDAO ud = new UserDAO();
			User user = ud.getUserById(id);
			if(user!=null)
			{
				try
				{
					ud.updateUser(updateUserPO, userId);
					updateUserVO.setSuccessMessage("The details of the user has been updated successfully");
				}
				catch(Exception e)
				{
					updateUserVO.setErrorMessage("Error in updating the details of the user");
				}
			}
			else
			{
				updateUserVO.setErrorMessage("Error! Attempting update for an unknown user with id: " + id);
			}
		}
		else
		{
			updateUserVO.setErrorMessage("Session Expired!");
		}
		String json = gson.toJson(updateUserVO);
		return Response.status(200).entity(json).build();
	}

	@POST
	@Path("/users/{id}/delete")
	public Response deleteUserById(@PathParam("id") Long id, String data) {
		Gson gson = new Gson();
		DeleteVO deleteVO = new DeleteVO();
		UserPO userPO = gson.fromJson(data, UserPO.class);
		String token = userPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		if (status) {
			UserDAO ud = new UserDAO();
			User user = ud.getUserById(id);
			if (user != null) {
				String type = user.getType();
				if(!(type.equals("admin")))
				{
					ud.deleteUser(id);
					deleteVO.setSuccessMessage("User Deleted Successfully");
				}
				else
				{
					deleteVO.setErrorMessage("Can't Delete User of role Admin");
				}
			} else {
				deleteVO.setErrorMessage("No such user present for id: " + id);
			}
		} else {
			deleteVO.setErrorMessage("Session Expired!");
		}
		String json = gson.toJson(deleteVO);
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(String data)
	{
		Gson gson = new Gson();
		UserPO userPO = gson.fromJson(data, UserPO.class);
		String json = null;
		String token = userPO.getAuthToken();
		AdminVO adminVO = new AdminVO();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		if(status)
		{
			UserAuthentication userAuthentication = new AuthenticationDAO().getUserAuthenticationByToken(token);
			User user = userAuthentication.getUser();
			String type = user.getType();
			if(type.equals("admin"))
			{
				UserDAO ud = new UserDAO();
				List<User> users = ud.getAllUsers();
				adminVO.setUserList(users);
				adminVO.setSuccessMessage("Users Fetched Successfully!");
				System.out.println(json);
			}
			else
			{
				adminVO.setErrorMessage("Authorization Error!");
			}
		}
		else
		{
			adminVO.setErrorMessage("Session Expired!");
		}
		json = gson.toJson(adminVO);
		return Response.status(Response.Status.OK).entity(json).build();
	}
	
	@POST
	@Path("users/new-user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(String data)
	{
		Gson gson = new Gson();
		String json = null;
		SignUpPO signupPO = gson.fromJson(data, SignUpPO.class);
		System.out.println(signupPO);
		String token = signupPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		SignUpVO signUpVO = new SignUpVO();
		if(status)
		{
			UserAuthentication userAuthentication = new AuthenticationDAO().getUserAuthenticationByToken(token);
			User user = userAuthentication.getUser();
			String type = user.getType();
			if(type.equals("admin"))
			{
				User user1 = new User();
				user1.setFname(signupPO.getFname());
				user1.setMname(signupPO.getMname());
				user1.setLname(signupPO.getLname());
				user1.setEmailId(signupPO.getEmailId());
				user1.setPassword(signupPO.getPassword());
				user1.setAddress(signupPO.getAddress());
				user1.setGender(signupPO.getGender());
				user1.setDob(signupPO.getDob());
				user1.setPhone(signupPO.getPhone());
				user1.setType(signupPO.getType());
				user1.setStatus(signupPO.getStatus());
				UserDAO ud = new UserDAO();
				try 
				{
					ud.addUser(user1);
					signUpVO.setSuccessMessage("User added successfully");
					json = gson.toJson(signUpVO);
					WelcomeEmail wEmail = new WelcomeEmail();
					wEmail.sendEmail(signupPO.getEmailId(), signupPO.getPassword());
				} 
				catch(ConstraintViolationException e) 
				{
					signUpVO.setErrorMessage("A user with this email address is already registered! Please use other email address or use forgot password");
					json = gson.toJson(signUpVO);
				}
				catch(Exception e) 
				{
					signUpVO.setErrorMessage("Errors in details! Please try again!");
					json = gson.toJson(signUpVO);
				}
			}
			else
			{
				signUpVO.setErrorMessage("You're not authorized to add user!");
				json = gson.toJson(signUpVO);
			}
		}
		else
		{
			signUpVO.setErrorMessage("Session Expired!");
		}
		
		return Response.status(200).entity(json).build();
	}
}