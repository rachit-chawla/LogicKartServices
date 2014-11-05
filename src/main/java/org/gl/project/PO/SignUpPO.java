package org.gl.project.PO;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import org.codehaus.jackson.map.annotate.JsonSerialize;
//import org.gl.project.Models.JsonDateSerializer;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class SignUpPO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z\\s']+", message="Please put characters in first name!")
	@Min(value=3, message="Minimum length of your first name should be 3 characters!")
	@Max(value=15, message="Maximum length of your first name should be 15 characters!")
	private String fname;
	
	@Pattern(regexp="[a-zA-Z\\s']+", message="Please put characters in middle name!")
	@Min(value=3, message="Minimum length of your middle name should be 3 characters!")
	@Max(value=15, message="Maximum length of your middle name should be 15 characters!")
	private String mname;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z\\s']+", message="Please put characters in last name!")
	@Min(value=3, message="Minimum length of your last name should be 3 characters!")
	@Max(value=15, message="Maximum length of your last name should be 15 characters!")
	private String lname;
	
	@NotNull
	@Email(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please put a valid email address")
	private String emailId;
	
	@NotNull
	private String password;
	
	//@JsonSerialize(using=JsonDateSerializer.class)
	private Date dob;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z\\s']+")
	private String gender;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="Please put a valid residential address")
	private String address;
	
	@NotNull
	@Pattern(regexp="[0-9]+")
	@Digits(integer=10, fraction=0, message="Please put digits in contact number")
	private String phone;
	
	@NotNull
	private String type = "user";
	
	@NotNull
	private String status = "enable";
	
	@NotNull
	private String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}