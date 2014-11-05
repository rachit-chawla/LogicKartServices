package org.gl.project.Services;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.UserDAO;
import org.gl.project.EmailServices.WelcomeEmail;
import org.gl.project.Models.User;
import org.gl.project.PO.SignUpPO;
import org.gl.project.VO.SignUpVO;
import org.hibernate.exception.ConstraintViolationException;

import com.google.gson.Gson;

@Path("/signup")
public class SignUpService 
{
	@POST
	@Path("/newuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(String data)
	{
		User user = new User();
		Gson gson = new Gson();
		String json = null;
		SignUpPO signupPO = gson.fromJson(data, SignUpPO.class);
		System.out.println(signupPO);
		String fname = signupPO.getFname();
		String mname = signupPO.getMname();
		String lname = signupPO.getLname();
		String emailId = signupPO.getEmailId();
		String password = signupPO.getPassword();
		String address = signupPO.getAddress();
		String gender = signupPO.getGender();
//		Date dob = signupPO.getDob();
		String phone = signupPO.getPhone();
		SignUpVO signUpVO = new SignUpVO();
		if(fname!=null && !(fname.equals("")) && !(fname.equals(" ")) && lname!=null && !(lname.equals("")) && !(lname.equals(" ")) && emailId!=null && !(emailId.equals("")) && !(emailId.equals(" ")) && password!=null && !(password.equals("")) && !(password.equals(" ")) && address!=null && !(address.equals("")) && !(address.equals(" ")) && gender!=null && !(gender.equals("")) && !(gender.equals(" ")) && phone!=null && !(phone.equals("")) && !(phone.equals(" ")))
		{
			user.setFname(fname);
			user.setMname(mname);
			user.setLname(lname);
			user.setEmailId(emailId);
			user.setPassword(password);
			user.setAddress(address);
			user.setGender(gender);
			user.setDob(signupPO.getDob());
			user.setPhone(phone);
			UserDAO ud = new UserDAO();
			try 
			{
				ud.addUser(user);
				signUpVO.setSuccessMessage("User added successfully");
				json = gson.toJson(signUpVO);
				WelcomeEmail wEmail = new WelcomeEmail();
				wEmail.sendEmail(signupPO.getEmailId(), signupPO.getPassword());
			} 
			catch(ConstraintViolationException e) 
			{
				signUpVO.setErrorMessage("A user with this email address is already registered! Please use other email address or use forgot password");
				json = gson.toJson(signUpVO);
			}
			catch(Exception ee)
			{
				System.out.println(ee);
				signUpVO.setErrorMessage("Error in adding user! Please try again!");
				json = gson.toJson(signUpVO);
			}
		}
		else
		{
			signUpVO.setErrorMessage("Please enter valid input!");
			json = gson.toJson(signUpVO);
		}
		return Response.status(200).entity(json).build();
	}
}