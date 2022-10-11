package com.dailycodebuffer.springemailclient;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	//Simple method to send simple mail without attachment
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		
		SimpleMailMessage message= new SimpleMailMessage();
	
		message.setFrom("apekshapachundkar@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
	   System.out.println("Simple Mail Send...");
	
	
	}
	
	//2nd method to send simple mail with attachment
	public void sendEmailWithAttachment(String toEmail,
			                            String body,
			                            String subject,
			                            String attachment ) throws MessagingException {
		
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);//here we are setting multipart as true because we are adding attachment
	
	    mimeMessageHelper.setFrom("apekshapachundkar@gmail.com");
	    mimeMessageHelper.setTo(toEmail);
	    mimeMessageHelper.setText(body);
	    mimeMessageHelper.setSubject(subject);
	    
	    FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
	    mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
	
	    mailSender.send(mimeMessage);
	    System.out.println("Mail Send with Attachment...");
		
	}
}
