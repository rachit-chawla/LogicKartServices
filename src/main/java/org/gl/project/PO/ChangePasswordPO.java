package org.gl.project.PO;

import java.io.Serializable;

public class ChangePasswordPO implements Serializable 
{
	private static final long serialVersionUID = 123L;
	
	private String oldPassword;

	private String newPassword;
	
	private String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}