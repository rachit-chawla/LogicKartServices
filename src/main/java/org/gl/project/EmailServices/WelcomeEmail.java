package org.gl.project.EmailServices;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class WelcomeEmail {

	private Properties props;
	private Session mailSession;
	private MimeMessage message;
	private String fromEmail = "logickartservice.no.reply@gmail.com";
	private String password = "logickart";
	private String subjectEmail = "Welcome to LogicKart";
	private String host = "smtp.gmail.com";
	
	public void sendEmail(String toemailId, String toPassword) throws AddressException, MessagingException
	{
		props = System.getProperties();
		props.put("mail.smtp.host", host); //SMTP HOST - GMAIL
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
//		mailSession = Session.getInstance(props, new javax.mail.Authenticator(){
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication()
//			{
//				return new PasswordAuthentication(fromEmail, password);
//			}
//		});
		
		mailSession = Session.getInstance(props, null);
		
		message = new MimeMessage(mailSession);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toemailId));
		message.setSubject(subjectEmail);
		String emailBody = "<h3>Welcome to LogicKart</h3><p> Your Email id: " + toemailId + "</p> <p> Your password: " + toPassword + "</p> <p> Regards, <br> Admin <br> LogicKart </p>";
		message.setContent(emailBody, "text/html");
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(host, fromEmail, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}	
}