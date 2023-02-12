package com.spring.backend.easyvet.model.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
	
	@Autowired
    private JavaMailSender emailSender;

	public void sendWelcomeEmail(String email, String fullname) {
		
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        try {
        	helper.setTo(email);
            helper.setSubject("Welcome to EsyVet, " + fullname );
            helper.setText("<html>" +
                "<head>" +
                "  <style>" +
                "    .header {" +
                "      text-align: center;" +
                "      font-size: 20px;" +
                "    }" +
                "    .subtitle {" +
                "      text-align: center;" +
                "      font-size: 16px;" +
                "      margin-top: 20px;" +
                "    }" +
                "    .image {" +
                "      display: block;" +
                "      margin-left: auto;" +
                "      margin-right: auto;" +
                "      width: 50%;" +
                "    }" +
                "    .footer {" +
                "      text-align: center;" +
                "      font-size: 14px;" +
                "      margin-top: 20px;" +
                "    }" +
                "  </style>" +
                "</head>" +
                "<body>" +
                "  <div class='header'>Your register on easyvet was successfull.</div>" +
                "  <div class='subtitle'>You can learn more about us on www.easyvet.com</div>" +
                "  <div class='footer'>All rights reserved</div>" +
                "</body>" +
                "</html>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
	}

}
