package com.lognsys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;

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

	public void processData(DailyLogDTO dailyLogDTO, String from,String crudmessage) {
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println("processData --list.length() "+dailyLogDTO.getAttendby());
		System.out.println("processData --crudmessage "+crudmessage);
			
	{
			String[] too=dailyLogDTO.getAttendby().split(", ");
			message.setFrom(from);
			message.setTo(too);
			message.setSubject(crudmessage+" : "+dailyLogDTO.getAssign_task_title());
			message.setText(dailyLogDTO.getDescription());
			System.out.println("processData --message "+message);
			
			mailSender.send(message);
			System.out.println("processData --mailSender "+mailSender);
			
		}
	}
}
