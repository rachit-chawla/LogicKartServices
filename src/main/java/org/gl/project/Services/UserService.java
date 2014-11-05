package org.gl.project.Services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.UserDAO;
import org.gl.project.Models.User;
import org.gl.project.Models.UserAuthenticationCheck;
import org.gl.project.PO.ChangePasswordPO;
import org.gl.project.PO.UpdateUserPO;
import org.gl.project.PO.UserPO;
import org.gl.project.VO.ChangePasswordVO;
import org.gl.project.VO.UpdateUserVO;
import org.gl.project.VO.UserDetailsVO;
import org.hibernate.exception.ConstraintViolationException;

import com.google.gson.Gson;

@Path("/users")
public class UserService {

	@POST
	@Path("/{id}/view")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserById(@PathParam("id") Long id, String data)
	{
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
				udv.setFname(user.getFname());
				udv.setMname(user.getMname());
				udv.setLname(user.getLname());
				udv.setAddress(user.getAddress());
				udv.setDob(user.getDob());
				udv.setGender(user.getGender());
				udv.setPhone(user.getPhone());
				udv.setEmailId(user.getEmailId());
				udv.setPassword(user.getPassword());
				udv.setSuccessMessage("User Details fetched successfully");
			}
			else
			{
				udv.setErrorMessage("No User with id: " + id);
			}
		}
		else
		{
			udv.setErrorMessage("Session Expired!");
		}
			String json = gson.toJson(udv);
			System.out.println(udv);
			return Response.status(200).entity(json).build();
	}
	
	@POST
	@Path("/{id}/change-password")
	public Response changePassword(@PathParam("id") Long id, String data)
	{
		UserDAO ud = new UserDAO();
		Long userId = id;
		User user = ud.getUserById(userId);
		Gson gson = new Gson();
		ChangePasswordPO changePasswordPO = (ChangePasswordPO)UserService.getObject(data,ChangePasswordPO.class);
		ChangePasswordVO changePasswordVo = new ChangePasswordVO();
		String opwd = changePasswordPO.getOldPassword();
		String npwd = changePasswordPO.getNewPassword();
		if(user!=null)
		{
			String temp = user.getPassword();
			String emaildId = user.getEmailId();
			if(!(temp.equals(opwd)))
			{
				changePasswordVo.setSuccessMessage("The old pasword doesn't match");
				String json = gson.toJson(changePasswordVo);
				return Response.status(200).entity(json).build();
			}
			else
			{
				ud.updateUserPassword(user, npwd, emaildId);
				changePasswordVo.setSuccessMessage("Password changed successfully");
				String json = gson.toJson(changePasswordVo);
				return Response.status(200).entity(json).build();
			}
		}
		else
		{
			changePasswordVo.setSuccessMessage("Error! Attempting Change Password for unknown user!");
			String json = gson.toJson(changePasswordVo);
			return Response.status(200).entity(json).build();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObject(String data,Class clazz) {
		Gson gson = new Gson();
		return (Object)gson.fromJson(data, clazz);
	}
	
	@POST
	@Path("/{id}/update")
	public Response updateUser(@PathParam("id") Long id, String data) //@FormParam("fname") String fname, @FormParam("mname") String mname, @FormParam("lname") String lname, @FormParam("dob") Date dob, @FormParam("gender") String gender, @FormParam("address") String address, @FormParam("phone") String phone)
	{
		Gson gson = new Gson();
		Long userId = id;
		UpdateUserPO updateUserPO = gson.fromJson(data, UpdateUserPO.class);
		UserDAO ud = new UserDAO();
		String token = updateUserPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		User user = ud.getUserById(id);
		UpdateUserVO updateUserVO = new UpdateUserVO();
		if(status)
		{
			if(user!=null)
			{
				try
				{
					ud.updateUser(updateUserPO, userId);
					updateUserVO.setSuccessMessage("User Details have been updated successfully");
				}
				catch(ConstraintViolationException e) 
				{
					updateUserVO.setErrorMessage("A user with this email address is already registered! Please use other email address to update the user");
				}
				catch(Exception e)
				{
					updateUserVO.setErrorMessage("Error in updating the details of the user : " + e);
				}
			}
			else
			{
				updateUserVO.setErrorMessage("Error! Attempting update for an unknow user!");
			}
		}
		else
		{
			updateUserVO.setErrorMessage("Session Expired!");
		}
		String json = gson.toJson(updateUserVO);
		return Response.status(Response.Status.OK).entity(json).build();
	}
}