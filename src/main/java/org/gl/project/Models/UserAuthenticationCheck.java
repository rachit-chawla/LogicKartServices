package org.gl.project.Models;

import org.gl.project.DAO.AuthenticationDAO;

public class UserAuthenticationCheck {
	public static boolean isSessionAlive(String token)
	{
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();
		UserAuthentication userAuthentication = authenticationDAO.getUserAuthenticationByToken(token);
		String status = userAuthentication.getStatus();
		if(status.equals("Active"))
		{
			return true;
		}
		return false;
	}
}
