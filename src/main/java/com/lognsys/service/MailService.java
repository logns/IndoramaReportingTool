package com.lognsys.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailservice")
public class MailService {
	// Injecting resource application.properties.
	@Autowired
	private MailSender mailSender;

	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println("sendMail --from "+from);
		System.out.println("sendMail --to "+to);
		System.out.println("sendMail --subject "+subject);
		System.out.println("sendMail --msg "+msg);
		
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		System.out.println("sendMail --message "+message);
		
		mailSender.send(message);
		System.out.println("sendMail --mailSender "+mailSender);
		
	}
}
