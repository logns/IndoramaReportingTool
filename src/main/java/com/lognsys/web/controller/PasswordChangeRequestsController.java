package com.lognsys.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.PasswordChangeRequestsDTO;
import com.lognsys.dao.jdbc.JdbcPasswordChangeRequestsRepository;
import com.lognsys.exception.CustomGenericException;
import com.lognsys.model.PasswordChangeRequests;
import com.lognsys.model.Users;
import com.lognsys.service.PasswordChangeRequestsService;
import com.lognsys.service.UserService;
import com.lognsys.util.Constants;
import com.lognsys.util.FormValidator;

@Controller
public class PasswordChangeRequestsController {
//
//	@Autowired
//	@Qualifier("applicationProperties")
//	private Properties applicationProperties;
//
//	@Autowired
//	private JdbcPasswordChangeRequestsRepository jdbcPCRRepository;
//
//	@Autowired
//	private PasswordChangeRequestsService passwordChangeRequestsService;
//
//	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
//	public String showForgotPassword(Model model, HttpServletRequest request) {
//		Users email = new Users();
//
//		model.addAttribute("email", email);
//		return "forgotpassword";
//	}
//
//	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
//	public String forgotPassword(@ModelAttribute("email") Users users, BindingResult result, ModelMap model) {
//		try {
//
//			System.out.println("forgotPassword --users " + users.getUsername());
//
//			FormValidator formValidator = new FormValidator();
//			formValidator.validate(users.getUsername(), result);
//			if (result.hasErrors()) {
//				Users email = new Users();
//				model.addAttribute("email", email);
//				return "forgotpassword";
//			} else {
//				if (users.getUsername() != null && users.getUsername().length() > 0) {
//					boolean isExist = passwordChangeRequestsService.findUsernameIsExist(users.getUsername());
//					if (isExist) {
//						passwordChangeRequestsService.forgotPassword(users);
//						return "login";
//
//					} else {
//						model.addAttribute("error", "No email id found. Please Register Your self first");
//						return "forgotpassword";
//
//					}
//				}
//
//			}
//		} catch (EmptyResultDataAccessException e) {
//			throw new CustomGenericException(applicationProperties
//					.getProperty(Constants.EXCEPTIONS_MSG.data_access_exception_forgotpassword.name()));
//
//		}
//		return "login";
//
//	}
//
//	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
//	public String showResetPassword(@RequestParam("id") String hash_id, Model model, HttpServletRequest request) {
//		try {
//			PasswordChangeRequests pcrs = new PasswordChangeRequests();
//			pcrs.setHash_id(hash_id);
//			model.addAttribute("pcrs", pcrs);
//			return "resetpassword";
//		} catch (Exception e) {
//			System.out.println("\n Exception showResetPassword resetpassword \n \n " + e.toString());
//			// customgenericException class for the exception to be viewed in
//			// web page
//			throw new CustomGenericException(
//					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));
//		}
//	}
//
//	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
//	public ModelAndView updateResetPassword(@ModelAttribute("pcrs") PasswordChangeRequests pcrs, BindingResult result,
//			ModelAndView model) throws ParseException {
//		try {
//			model = new ModelAndView();
//
//			if (pcrs.getPassword() == null || pcrs.getPassword().trim().isEmpty()) {
//				model.addObject("error", "Incorrect format of password");
//				model.setViewName("resetpassword");
//				return model;
//			} else {
//
//				Pattern p = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})");
//				Matcher m = p.matcher(pcrs.getPassword());
//				// boolean b = m.matches();
//				boolean b = m.find();
//				System.out.println("resetpassword -- POST b============== " + b);
//
//				if (b == true) {
//					// model.addObject("error", "There is a special character in
//					// password");
//					String hashStringId = passwordChangeRequestsService.generateHashShakeyFromString(pcrs.getHash_id());
//					Calendar calendar = Calendar.getInstance(); // current time
//
//					PasswordChangeRequestsDTO passwordChangeRequestsDTO = jdbcPCRRepository
//							.findPCRByHash_id(hashStringId);
//					System.out.println("\n resetpassword -- POST   passwordChangeRequestsDTO : "
//							+ passwordChangeRequestsDTO.toString());
//
//					if (passwordChangeRequestsDTO.getNo_of_attempts() >= 3) {
//						System.out.println("\nresetpassword -- POST Current time == " + calendar.getTime());
//						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//						String currentdate = sdf.format(calendar.getTime());
//						System.out.println("\nresetpassword -- POST Current currentdate == " + currentdate);
//
//						// HH converts hour in 24 hours format (0-23), day
//						// calculation
//						SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//						Date date = new Date();
//						String dateStart = passwordChangeRequestsDTO.getTime();
//						String dateStop = currentdate;
//						System.out.println("\nresetpassword -- POST Current dateStart == " + dateStart);
//						System.out.println("\nresetpassword -- POST Current dateStop == " + dateStop);
//
//						Date d1 = null;
//						Date d2 = null;
//
//						try {
//							d1 = format.parse(dateStart);
//							d2 = format.parse(dateStop);
//							System.out.println("\nresetpassword -- POST Current dateStart d1 == " + d1);
//							System.out.println("\nresetpassword -- POST Current dateStop d2== " + d2);
//
//							// in milliseconds
//							long diff = d2.getTime() - d1.getTime();
//
//							long diffSeconds = diff / 1000 % 60;
//							long diffMinutes = diff / (60 * 1000) % 60;
//							System.out.println("\nresetpassword -- POST Current diffMinutes== " + diffMinutes);
//
//							long diffHours = diff / (60 * 60 * 1000) % 24;
//							long diffDays = diff / (24 * 60 * 60 * 1000);
//
//							model = new ModelAndView();
//							System.out.println("\nresetpassword -- POST Current diffHours== " + diffHours);
//							System.out.println("\nresetpassword -- POST Current diffMinutes+diffHours*60== "
//									+ (diffMinutes + diffHours * 60));
//
//							if (diffMinutes >= 18000 || diffHours == 3) {
//								pcrs.setTime(dateStop);
//								System.out.println(
//										"\nresetpassword -- POST Current UpdateAndRetriveUsersAndPCRRecords== ");
//
//								passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
//								model.setViewName("login");
//
//							} else {
//								model.addObject("error", "Please try after 3 hours to reset your password");
//								model.setViewName("resetpassword");
//							}
//							return model;
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					} else {
//						passwordChangeRequestsService.UpdateAndRetriveUsersAndPCRRecords(pcrs, hashStringId);
//
//					}
//				} else {
//					model.addObject("error",
//							"Password must contain atleast 1 special  character. \n 1 Upper and Lower case alphabet and \nnumbers");
//					model.setViewName("resetpassword");
//					return model;
//				}
//				model.setViewName("login");
//				return model;
//			}
//		} catch (Exception e) {
//			System.out.println("\n Exception showAssignTasks assigntasklist \n \n " + e.toString());
//			// customgenericException class for the exception to be viewed in
//			// web page
//			throw new CustomGenericException(
//					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));
//		}
//	}
//
//	// @ExceptionHandler to decide which �view� should be returned back if
//	// certain exception is raised
//	@ExceptionHandler(CustomGenericException.class)
//	public ModelAndView handleCustomException(CustomGenericException ex) {
//
//		ModelAndView model = new ModelAndView("generic_error");
//		model.addObject("errMsg", ex.getErrMsg());
//
//		System.out.println("\n CustomGenericException handleCustomException assigntasklist  ex \n \n " + ex.toString());
//		System.out.println("\n CustomGenericException handleCustomException assigntasklist  model \n \n " + model);
//		return model;
//
//	}
//
//	// all kind of exception is shown via this method
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleAllException(Exception ex) {
//		System.out.println("\n CustomGenericException handleAllException  assigntasklist ex \n \n " + ex.toString());
//
//		ModelAndView model = new ModelAndView("generic_error");
//		model.addObject("errMsg", "this is Exception.class");
//
//		System.out.println("\n CustomGenericException handleAllException assigntasklist model \n \n " + model);
//
//		return model;
//
//	}

}