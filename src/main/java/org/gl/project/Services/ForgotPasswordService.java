package org.gl.project.Services;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gl.project.DAO.UserDAO;
import org.gl.project.EmailServices.ForgotPasswordEmailService;
import org.gl.project.Models.User;
import org.gl.project.PO.ForgotPasswordPO;
import org.gl.project.VO.ForgotPasswordVO;

import com.google.gson.Gson;

@Path("/forgot-password")
public class ForgotPasswordService 
{
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(String data) throws AddressException, MessagingException//@FormParam("emailId") String emailId) throws AddressException, MessagingException
	{
		Gson gson = new Gson();
		String json = null;
		ForgotPasswordPO fpPO = gson.fromJson(data, ForgotPasswordPO.class);
		UserDAO ud = new UserDAO();
		String emailId = fpPO.getEmailId();
		ForgotPasswordVO fpVO = new ForgotPasswordVO();
		if(emailId!=null && !(emailId.equals("")) && !(emailId.equals(" ")))
		{	
			User user = ud.getUserByEmailId(emailId);
			if(user!=null)
			{
				String newPassword = ud.getNewPassword();
				ud.updateUserPassword(user, newPassword, emailId);
				ForgotPasswordEmailService  forgotpasswordEmail = new ForgotPasswordEmailService();
				forgotpasswordEmail.sendEmail(user.getFname(), newPassword, emailId);
				fpVO.setEmailId(emailId);
				fpVO.setSuccessMesage("Your password has been reset. Please Check your mail");
				json = gson.toJson(fpVO);
			}
			else
			{
				fpVO.setErrorMessage("Sorry, but couldn't find any record!");
				json = gson.toJson(fpVO);
				
			}
		}
		else
		{
			fpVO.setErrorMessage("Please enter valid input!");
			json = gson.toJson(fpVO);
		}
		return Response.status(200).entity(json).build();
	}
}