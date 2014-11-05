package org.gl.project.Services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.AuthenticationDAO;
import org.gl.project.Models.UserAuthenticationCheck;
import org.gl.project.PO.LogoutPO;
import org.gl.project.VO.LogoutVO;

import com.google.gson.Gson;

@Path("/logout")
public class LogoutService {

	@POST
	public Response logoutUser(String data)
	{
		Gson gson = new Gson();
		LogoutPO logoutPO = gson.fromJson(data, LogoutPO.class);
		String token = logoutPO.getAuthToken();
		boolean status = UserAuthenticationCheck.isSessionAlive(token);
		LogoutVO logoutVO = new LogoutVO();
		String json = null;
		if(status)
		{
			AuthenticationDAO authDAO = new AuthenticationDAO();
			try
			{
				authDAO.ExpireUserAuthentication(token);
				logoutVO.setSuccessMessage("You have been logged out successfully!");
			}
			catch(Exception e)
			{
				logoutVO.setErrorMessage("Error in Logging Out");
			}
			json = gson.toJson(logoutVO);
			return Response.status(200).entity(json).build();
		}
		else
		{
			logoutVO.setErrorMessage("Login Again!");
			json = gson.toJson(logoutVO);
			return Response.status(200).entity(json).build();
		}	
	}
}
