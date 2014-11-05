package org.gl.project.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String fname;
	private String mname;
	@Column(nullable=false)
	private String lname;
	@Column(nullable=false, unique=true)
	private String emailId;
	@Column(nullable=false)
	private String password;
    private Date dob;
    @Column(nullable=false)
	private String gender;
    @Column(nullable=false)
	private String address;
    @Column(nullable=false)
	private String phone;
	private String type = "user";
	private String status = "enable";
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Orders> orders = new ArrayList<Orders>();
	
//	@OneToMany(mappedBy="authenticatedUser", fetch=FetchType.LAZY)
//	private List<UserAuthentication> authenticationTokenSet = new ArrayList<UserAuthentication>();
//		
//	public List<UserAuthentication> getAuthenticationTokenSet() {
//		return authenticationTokenSet;
//	}
//	public void setAuthenticationTokenSet(
//			List<UserAuthentication> authenticationTokenSet) {
//		this.authenticationTokenSet = authenticationTokenSet;
//	}
	public Long getId() {
		return id;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", mname=" + mname
				+ ", lname=" + lname + ", emailId=" + emailId + ", password="
				+ password + ", dob=" + dob + ", gender=" + gender
				+ ", address=" + address + ", phone=" + phone + ", type="
				+ type + ", orders=" + orders + "]";
	}
}
