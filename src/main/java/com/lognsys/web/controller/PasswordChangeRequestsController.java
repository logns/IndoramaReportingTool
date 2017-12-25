package com.lognsys.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.PasswordChangeRequestsDTO;
import com.lognsys.dao.jdbc.JdbcPasswordChangeRequestsRepository;
import com.lognsys.model.PasswordChangeRequests;
import com.lognsys.model.Users;
import com.lognsys.service.PasswordChangeRequestsService;
import com.lognsys.service.UserService;

@Controller
public class PasswordChangeRequestsController {


	@Autowired
	private JdbcPasswordChangeRequestsRepository jdbcPCRRepository;

	@Autowired
	private PasswordChangeRequestsService passwordChangeRequestsService;
	
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String showForgotPassword(Model model, HttpServletRequest request) {
		Users email =new Users();
		
		model.addAttribute("email", email);
		return "forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("email") Users users, BindingResult result, ModelMap model){
		System.out.println("forgotPassword --users "+users.getUsername());
		passwordChangeRequestsService.forgotPassword(users);

		return "login";
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String showResetPassword(@RequestParam("id") String hash_id,Model model, HttpServletRequest request) {
		PasswordChangeRequests pcrs =new PasswordChangeRequests();
		pcrs.setHash_id(hash_id);
		model.addAttribute("pcrs", pcrs);
		return "resetpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ModelAndView updateResetPassword(@ModelAttribute("pcrs") PasswordChangeRequests pcrs, BindingResult result, ModelAndView model) throws ParseException{
		System.out.println("resetpassword -- POST tostring "+pcrs.toString());
		
		String hashStringId=passwordChangeRequestsService.generateHashShakeyFromString(pcrs.getHash_id());
	
		Calendar calendar=Calendar.getInstance(); // current time
    	
		PasswordChangeRequestsDTO passwordChangeRequestsDTO =jdbcPCRRepository.findPCRByHash_id(hashStringId);
		System.out.println("\n resetpassword -- POST   passwordChangeRequestsDTO : " + passwordChangeRequestsDTO.toString());
	    
		if(passwordChangeRequestsDTO.getNo_of_attempts()>=3){
			System.out.println("\nresetpassword -- POST Current time == "+calendar.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		     String currentdate =  sdf.format(calendar.getTime()); 
		     System.out.println("\nresetpassword -- POST Current currentdate == "+currentdate);
				 
			//HH converts hour in 24 hours format (0-23), day calculation
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date =new Date();
			String dateStart =passwordChangeRequestsDTO.getTime();
			String dateStop ="25-12-2017 16:10:50 " ;//currentdate;
			System.out.println("\nresetpassword -- POST Current dateStart == "+dateStart);
			System.out.println("\nresetpassword -- POST Current dateStop == "+dateStop);
			

			Date d1 = null;
			Date d2 = null;

			try {
				d1 = format.parse(dateStart);
				d2 = format.parse(dateStop);
				System.out.println("\nresetpassword -- POST Current dateStart d1 == "+d1);
				System.out.println("\nresetpassword -- POST Current dateStop d2== "+d2);
				
				//in milliseconds
				long diff = d2.getTime() - d1.getTime();
				
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				System.out.println("\nresetpassword -- POST Current diffMinutes== "+diffMinutes);
				
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);

				model = new ModelAndView();
				System.out.println("\nresetpassword -- POST Current diffHours== "+diffHours);
				System.out.println("\nresetpassword -- POST Current diffMinutes+diffHours*60== "+(diffMinutes+diffHours*60));
				

				if(diffMinutes>=18000 || diffHours==3){
					pcrs.setTime(dateStop);
					System.out.println("\nresetpassword -- POST Current UpdateAndRetriveUsersAndPCRRecords== ");
					
				 	passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
					 model.setViewName("login");
						
				}
				else{
					model.addObject("error", "Please try after 3 hours to reset your password");
					 model.setViewName("resetpassword");
				}
				return model;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else{
	    	passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
		    
	    }
		model = new ModelAndView();
		 model.setViewName("login");
		return model;
	
	}

}
