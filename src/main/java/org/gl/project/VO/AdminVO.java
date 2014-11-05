package org.gl.project.VO;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.gl.project.Models.User;

public class AdminVO implements Serializable
{
	private static final long serialVersionUID = 1234L;
	
	@XmlElementWrapper(name="users")
	@XmlElement(name="user")
	private List<User> userList;
	private String successMessage;
	private String errorMessage;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
