package org.gl.project.PO;

import java.io.Serializable;

public class LoginPO implements Serializable 
{
	private static final long serialVersionUID = 123L;
	
	private String emailId;
	
	private String password;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}