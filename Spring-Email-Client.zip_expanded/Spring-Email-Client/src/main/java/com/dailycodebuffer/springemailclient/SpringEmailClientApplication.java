package com.dailycodebuffer.springemailclient;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailClientApplication.class, args);
	}

	@Autowired
	private EmailSenderService emailSenderService;
	
	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		 emailSenderService.sendSimpleEmail("apekshapachundkar1603@gmail.com",
				 "This is simple Email Body..." ,
				 "This is simple Email Subject..");
	}*/
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMailWithAttachment() throws MessagingException {
		 emailSenderService.sendEmailWithAttachment("apekshapachundkar1603@gmail.com",
				 "This is simple Email Body..." ,
				 "This is simple Email Subject..",
				 "C:\\Users\\APEKSHA\\Downloads\\thank-you-gif-8.gif");
	}
}
