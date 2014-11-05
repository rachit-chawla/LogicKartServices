package org.gl.project.VO;

public class ForgotPasswordVO {
	
	private String emailId;
	private String successMesage;
	private String errorMessage;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSuccessMesage() {
		return successMesage;
	}
	public void setSuccessMesage(String successMesage) {
		this.successMesage = successMesage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}