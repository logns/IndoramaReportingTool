package com.lognsys.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
		System.out.println("showForgotPassword --mailservice ");
		
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
		System.out.println("resetpassword --hash_id "+hash_id);
		pcrs.setHash_id(hash_id);
		System.out.println("resetpassword --mailservice ");
		model.addAttribute("pcrs", pcrs);
		return "resetpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String updateResetPassword(@ModelAttribute("pcrs") PasswordChangeRequests pcrs, BindingResult result, ModelMap model) throws ParseException{
		System.out.println("resetpassword -- POST tostring "+pcrs.toString());
		
		String hashStringId=passwordChangeRequestsService.generateHashShakeyFromString(pcrs.getHash_id());
	
		Calendar calendar=Calendar.getInstance(); // current time
    	System.out.println("\nresetpassword -- POST Current time == "+calendar.getTime());
    	
		PasswordChangeRequestsDTO passwordChangeRequestsDTO =jdbcPCRRepository.findPCRByHash_id(hashStringId);
		System.out.println("\n resetpassword -- POST   passwordChangeRequestsDTO : " + passwordChangeRequestsDTO.toString());
	    
		if(passwordChangeRequestsDTO.getNo_of_attempts()>=3){
	    	
	    	Calendar calendarAddhour=Calendar.getInstance(); // current time
	    	
	    	calendarAddhour.add(Calendar.HOUR,3); // add 3 minutes to current time
	    	
	    	System.out.println("\nresetpassword -- POST Current time == "+calendarAddhour.getTime());

	    	System.out.println("\nresetpassword -- POST compare time == "+
	    			(calendar.getTime().toString().equalsIgnoreCase(calendarAddhour.getTime().toString())));
	    	
	    	if(calendar.getTime().toString().equalsIgnoreCase(calendarAddhour.getTime().toString())){
		    	System.out.println("unblocking==");
		    	passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
		    }	
		    else{
		    	System.out.println("blocked==");
			}
	    }
	    else{
	    	passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
		    
	    }
		return "login";
	}

}
