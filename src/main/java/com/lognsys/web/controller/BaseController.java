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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.service.MailService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;

@Controller
public class BaseController {

//	private Logger LOG = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	Authentication  authentication;

	@Autowired
	private MessageSource msg;
	/**
	 * 
	 * This method redirects to login page
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView showLogin( HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		System.out.println("\nisRememberMeAuthenticated()"+isRememberMeAuthenticated());
		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("login");
			
		}
		else{
			model.setViewName("login");
			
		}
		return model;

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
		return "login";
	}
	/**
	 * This method goes to login page if wrong credentials or empty credentials
	 * else goes to Dahsbaord page
	 * 
	 * @param error
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
			//login form for update, if login error, get the targetUrl from session again.
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}
			model.setViewName("login");
		} else {
			System.out.println("\n login success +model "+model);
			
			model.setViewName("dashboard");
			return model;
		}

		return model;
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

				model.addAttribute("users", users);
				model.addAttribute("rolesList", rolesList);
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

			//populate to JSP page
			model.addAttribute("rolesList", rolesList);
			model.addAttribute("busList", busList);
			return "register";

		} 
		else
		{
		userService.addUser(user);
		authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("adduser  user role - "+(authentication.getPrincipal().toString()));
		String usernamelogged=ObjectMapper.authorizedUserName();
		System.out.println("adduser  user usernamelogged - "+(usernamelogged));
		
		if(authentication.getPrincipal().toString()!=null && usernamelogged!=null){
			return "userlist";
		}
		else{
			return "login";
		}
		}
	}
	/**
	 * Check if user is login by remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {

		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		System.out.println("isRememberMeAuthenticated authentication"+authentication);
		if (authentication == null) {
			return false;
		}
		System.out.println("isRememberMeAuthenticated RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass() ==="+RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass()));
		
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		System.out.println("\nsetRememberMeTargetUrlToSession()"+session);
		
		if(session!=null){
			session.setAttribute("targetUrl", "/dashboard");
		}
	}

	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}

}
