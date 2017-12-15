package com.lognsys.web.controller;

/**
 * Description : This is Base controller which serves the handling of 
 * login, users request and responds to appropriate view.
 * 
 * NOTE : No global variable should be defined!!!
 * 
 * Default: Spring mvc Controller is Singleton Class. 
 * 
 * @author pdoshi
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.model.Users;
import com.lognsys.service.MailService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;

@Controller
public class BaseController {

//	private Logger LOG = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@Autowired
	MailService mailservice;
	/**
	 * 
	 * This method redirects to login page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showLogin(Model model, HttpServletRequest request) {
		return "login";
	}
	/**
	 * 
	 * This method redirects to loggedout  page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/loggedout")
	public String showLoggedout(Model model, HttpServletRequest request) {
		return "loggedout";
	}
	/**
	 * This method goes to login page if wrong credentials or empty credentials
	 * else goes to Dahsbaord page
	 * 
	 * @param error
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
			model.setViewName("login");
		} else {
			model.setViewName("dashboard");
			return model;
		}

		return model;
	}
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String showForgotPassword(Model model, HttpServletRequest request) {
		Users email =new Users();
		System.out.println("showForgotPassword --mailservice ");
		
		model.addAttribute("email", email);
		return "forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("email") Users email, BindingResult result, ModelMap model){
		System.out.println("forgotPassword --email "+email.getUsername());
		String emailid=email.getUsername();
				System.out.println("forgotPassword --emailid "+emailid);
		
				mailservice.sendMail("from@gmail.com",
				emailid,
	    		   "Testing123",
	    		   "Testing only \n\n Hello Spring Email Sender");

		System.out.println("forgotPassword --mailservice "+mailservice);
		

		return "login";
	}


	/**
	 * Returns to Dashboard page
	 * 
	 * @param Model
	 * @param HttpServeltRequest
	 * @return
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model, HttpServletRequest request) {
		return "dashboard";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/edituser" }, method = RequestMethod.GET)
	public String editUsers(Model model, HttpServletRequest request) {
		return "edituser";
	}

	/**
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping(value = { "/edituser" }, method = RequestMethod.POST)
	public String editUsers(@ModelAttribute("editUser") Users users) {

		userService.updateUser(users);
		return "userlist";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String showUsers(Model model, HttpServletRequest request) throws IOException {
		userService.refreshUserList();
		return "userlist";
	}

	/**
	 * 
	 * @param model
	 * @param userIds
	 * @param userAction
	 * @return
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.POST)
	public String manageUser(Model model, @RequestParam(value = "userIds", required = false) String userIds,
			@RequestParam String userAction) {

		switch (userAction) {

		case "delete":
			JSONParser parser = new JSONParser();
			try {

				Object obj = parser.parse(userIds);

				JSONArray arr = (JSONArray) obj;

				String[] emailIDs = new String[arr.size()];
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					emailIDs[i] = jsonObject.get("email").toString();

				}
				userService.deleteUsers(emailIDs);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "userlist";

		case "edit":

			JSONParser p = new JSONParser();
			try {
				Object obj = p.parse(userIds);
				JSONArray arr = (JSONArray) obj;
				String id = "";
				for (int i = 0; i < arr.size(); i++) {

					JSONObject jsonObject = (JSONObject) arr.get(i);
					id = jsonObject.get("id").toString();
				}

				Users users = userService.getUserWithRoleAndGroup(Integer.parseInt(id));

				// CALL database to get roles & groups
				List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
//				List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
				List<BuDTO> listOfBuDTO = userService.getAllBus();

				// Adding data to list from RolesDTO
				List<String> rolesList = new ArrayList<String>();
				for (RolesDTO role : listOfRolesDTO) {
					rolesList.add(role.getRole());
				}

				// Adding data to list from RolesDTO
				List<String> busList = new ArrayList<String>();
				for (BuDTO bu : listOfBuDTO) {
					busList.add(bu.getBu_name());
				}
				// Adding data to list from RolesDTO
				List<String> departmentsList = new ArrayList<String>();
//				for (DepartmentsDTO deDto : listOfDepartmentsDTO) {
//					departmentsList.add(deDto.getDepartment_name());
//				}


				model.addAttribute("users", users);
				model.addAttribute("rolesList", rolesList);
				model.addAttribute("departmentsList", departmentsList);
				model.addAttribute("busList", busList);

				return "register";
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "userlist";
		}
		return "dashboard";
	}

	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterForm(Model model, HttpServletRequest request) {

		// CALL database to get roles & groups
		List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
//		List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
		List<BuDTO> listOfBuDTO = userService.getAllBus();

		// Adding data to list from RolesDTO
		List<String> rolesList = new ArrayList<String>();
		for (RolesDTO role : listOfRolesDTO) {
			rolesList.add(role.getRole());
		}

		// Adding data to list from RolesDTO
		List<String> busList = new ArrayList<String>();
		for (BuDTO bu : listOfBuDTO) {
			busList.add(bu.getBu_name());
		}
		// Adding data to list from RolesDTO
		List<String> departmentsList = new ArrayList<String>();
//		for (DepartmentsDTO deDto : listOfDepartmentsDTO) {
//			departmentsList.add(deDto.getDepartment_name());
//		}


		//populate to JSP page
		Users user = new Users();
		model.addAttribute("users", user);
		model.addAttribute("rolesList", rolesList);
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("busList", busList);

		return "register";
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("users") Users user, BindingResult result, ModelMap model) throws IOException {
		

		FormValidator formValidator = new FormValidator();
		formValidator.validate(user, result);

		if (result.hasErrors()) {
			System.out.println("Adding Errors - " + user.toString());
			// CALL database to get roles & groups
			List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
//			List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
			List<BuDTO> listOfBuDTO = userService.getAllBus();

			List<String> rolesList = new ArrayList<String>();
			for (RolesDTO role : listOfRolesDTO) {
				rolesList.add(role.getRole());
			}
			// Adding data to list from RolesDTO
			List<String> busList = new ArrayList<String>();
			for (BuDTO bu : listOfBuDTO) {
				busList.add(bu.getBu_name());
			}
			// Adding data to list from RolesDTO
			List<String> departmentsList = new ArrayList<String>();
//			for (DepartmentsDTO deDto : listOfDepartmentsDTO) {
//				departmentsList.add(deDto.getDepartment_name());
//			}


			//populate to JSP page
			model.addAttribute("rolesList", rolesList);
			model.addAttribute("busList", busList);
			return "register";

		} 
		else
		{
		userService.addUser(user);
		return "userlist";
		}
	}
}
