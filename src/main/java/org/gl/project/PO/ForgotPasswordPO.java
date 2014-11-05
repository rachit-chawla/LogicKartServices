package org.gl.project.PO;

import java.io.Serializable;

public class ForgotPasswordPO implements Serializable 
{
	private static final long serialVersionUID = 123L;
	
	private String emailId;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}