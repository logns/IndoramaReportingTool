package com.lognsys.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DepartmentsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.service.DailyLogService;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;

@Controller
public class DailyLogController {
private Logger LOG = Logger.getLogger(getClass());
	
	@Autowired
	private DailyLogService dailyLogService;
	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/dailylog", method = RequestMethod.GET)
	public String getDailyLogForm(ModelMap model, HttpServletRequest request) {
/*
		// CALL database to get roles & groups
		List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
		List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
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
		for (DepartmentsDTO deDto : listOfDepartmentsDTO)
		 {
			departmentsList.add(deDto.getDepartment_name());
		}*/
		String realname = "monika sharma";
		String bu1 = "bu1";
		String substation = "MS1";
		String shift = "Morning";

		//populate to JSP page
		DailyLog dailylogs = new DailyLog();
		model.addAttribute("dailylogs", dailylogs);
		model.addAttribute("bu1", bu1);
		model.addAttribute("realname", realname);
		model.addAttribute("substation", substation);
		model.addAttribute("shift", shift);
		return "dailylog";
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dailylog", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("dailylogs") DailyLog dailylogs, BindingResult result, ModelMap model) {
		System.out.println("controller dailylogs to String - " + dailylogs.toString());

		FormValidator formValidator = new FormValidator();
		formValidator.validate(dailylogs, result);

		if (result.hasErrors()) {
			/*System.out.println("Adding Errors - " + user.toString());
			// CALL database to get roles & groups
			List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
			List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
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
			for (DepartmentsDTO deDto : listOfDepartmentsDTO) {
				departmentsList.add(deDto.getDepartment_name());
			}*/


			//populate to JSP page
//			model.addAttribute("rolesList", rolesList);
//			model.addAttribute("departmentsList", departmentsList);
//			model.addAttribute("busList", busList);
			return "dailylog";

		} else {
			try {
			int id =dailyLogService.addDailyLog(dailylogs);
			System.out.println("controller id - " + id);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "dailylog";
	}

}
