package com.lognsys.web.controller;

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

import com.lognsys.model.Users;
import com.lognsys.service.PasswordChangeRequestsService;
import com.lognsys.service.UserService;

@Controller
public class PasswordChangeRequestsController {


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
		Users users =new Users();
		System.out.println("resetpassword --mailservice ");
		
		model.addAttribute("users", users);
		return "resetpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String updateResetPassword(@ModelAttribute("users") Users users,@RequestParam("id") String hash_id, BindingResult result, ModelMap model){
		System.out.println("resetpassword --users tostring "+users.toString());
		System.out.println("resetpassword --hash_id "+hash_id);
		
		String hashStringId=passwordChangeRequestsService.generateHashShakeyFromString(hash_id);
		System.out.println("resetpassword -- after hashing hashStringId "+hashStringId);
		
		passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(users, hashStringId);
		return "login";
	}

}
