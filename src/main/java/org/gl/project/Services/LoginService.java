package org.gl.project.Services;

import java.util.UUID;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.AuthenticationDAO;
import org.gl.project.DAO.UserDAO;
import org.gl.project.Models.User;
import org.gl.project.Models.UserAuthentication;
import org.gl.project.PO.LoginPO;
import org.gl.project.VO.LoginVO;

import com.google.gson.Gson;

@Path("/login")
public class LoginService 
{
	@SuppressWarnings("unused")
	@POST
	public Response authenticateUser(String data)
	{
		Gson gson = new Gson();
		LoginPO loginPO = gson.fromJson(data, LoginPO.class);
		String emailId = loginPO.getEmailId();
		String password = loginPO.getPassword();
		UserDAO ud = new UserDAO();
		User user = ud.getUserByEmailId(emailId);
  		String status = user.getStatus();
  		LoginVO loginVO = new LoginVO();
  		if(user!=null)
		{
			if(!(status.equalsIgnoreCase("disable")))
			{
				String pwd = user.getPassword();
				if(pwd.equals(password))
				{
					String randomToken = UUID.randomUUID().toString();
					AuthenticationDAO authDAO = new AuthenticationDAO();
					UserAuthentication auth = new UserAuthentication();
					auth.setUser(user);
					auth.setAuthenticationToken(randomToken);
					authDAO.addUserAuthentication(auth);
					loginVO.setId(user.getId());
 					loginVO.setFname(user.getFname());
					loginVO.setMname(user.getMname());
					loginVO.setLname(user.getLname());
					loginVO.setAddress(user.getAddress());
					loginVO.setEmailId(user.getEmailId());
					loginVO.setGender(user.getGender());
					loginVO.setPhone(user.getPhone());
					loginVO.setType(user.getType());
					loginVO.setStatus(user.getStatus());
					loginVO.setPassword(user.getPassword());
					loginVO.setAuthToken(randomToken);
					loginVO.setSuccessMessage("Welcome to LogicKart");
					String json = gson.toJson(loginVO);
					System.out.println(loginVO);
					return Response.status(200).entity(json).build();
				}
				else
				{
					loginVO.setErrorMessage("Credentials don't match");
					String json = gson.toJson(loginVO);
					return Response.status(200).entity(json).build();
				}
			}
			else
			{
				loginVO.setErrorMessage("Sorry, your credentials are disabled");
				String json = gson.toJson(loginVO);
				return Response.status(200).entity(json).build();
			}
		}
		else
		{
			loginVO.setErrorMessage("Please Register");
			String json = gson.toJson(loginVO);
			return Response.status(200).entity(json).build();
		}
	}
}
