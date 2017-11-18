package com.lognsys.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.AssignTaskService;
import com.lognsys.service.UserService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;


@Controller
public class AssignTaskController {
	
	@Autowired
	AssignTaskService assignTaskService;

	@Autowired
	UserService userService;
	
	List<UsersDTO> data=null;


	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/assigntasklist", method = RequestMethod.GET)
	public String showAssignTasks(Model model, HttpServletRequest request) throws IOException {
		assignTaskService.readAssignTask();
		return "assigntasklist";
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addtask", method = RequestMethod.GET)
	public String AddAssignTasks(Model model, HttpServletRequest request) throws IOException {

		String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
		String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
		String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
		String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());
		String str_priority = applicationProperties.getProperty(Constants.TYPES_ARRAY.priority.name());
		String str_done_percentage = applicationProperties.getProperty(Constants.TYPES_ARRAY.done_percentage.name());
		
		List<BuDTO> listOfBuDTO = userService.getAllBus();
		
		// Adding data to list from RolesDTO
		List<String> busList = new ArrayList<String>();
		for (BuDTO bu : listOfBuDTO) {
			busList.add(bu.getBu_name());
		}
		
		List<UsersDTO> listOfUsersDTO = userService.getUsers();
		
		// Adding data to list from RolesDTO
		List<String> usersList = new ArrayList<String>();
		for (UsersDTO user : listOfUsersDTO) {
			usersList.add(user.getRealname());
		}
		populateUsersListInJson(usersList);
		
		List<String> jobtype=Arrays.asList(str_jobtype.split(","));
		List<String> recordtype=Arrays.asList(str_recordtype.split(","));
		List<String> status=Arrays.asList(str_status.split(","));
		List<String> shift=Arrays.asList(str_shift.split(","));
		List<String> priority=Arrays.asList(str_priority.split(","));
		List<String> done_percentage=Arrays.asList(str_done_percentage.split(","));
		
		AssignTaskDailylogDTO atdl = new AssignTaskDailylogDTO();
		model.addAttribute("atdl", atdl);
		model.addAttribute("jobtype", jobtype);
		model.addAttribute("recordtype", recordtype);	
		model.addAttribute("status", status);
		model.addAttribute("shift", shift);
		model.addAttribute("priority", priority);
		model.addAttribute("done_percentage", done_percentage);
		model.addAttribute("busList", busList);
		model.addAttribute("usersList", usersList);
		
		return "addtask";
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
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("atdl") AssignTaskDailylogDTO atdldto, BindingResult result, ModelMap model) throws IOException {
		
		AssignTaskDTO assignTaskDTO=atdldto.getAssignTaskDTO();
		DailyLogDTO dailyLogDTO=atdldto.getDailylogDTO();

		dailyLogDTO.setAssign_task_title(assignTaskDTO.getTitle());
		dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date());
		dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage());
		System.out.println("\n Rest saveForm assignTaskDTO tostring  " + assignTaskDTO.toString());
		System.out.println("\n Rest saveForm dailyLogDTO tostring  " + dailyLogDTO.toString());
		if(assignTaskService.isexist(assignTaskDTO.getTitle())){
			
			FormValidator formValidator = new FormValidator();
			formValidator.validate(atdldto, result);
			formValidator.validate(dailyLogDTO, result);

			if (result.hasErrors()) {
				String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
				String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
				String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
				String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());
				String str_priority = applicationProperties.getProperty(Constants.TYPES_ARRAY.priority.name());
				String str_done_percentage = applicationProperties.getProperty(Constants.TYPES_ARRAY.done_percentage.name());
				
				List<BuDTO> listOfBuDTO = userService.getAllBus();
				
				// Adding data to list from RolesDTO
				List<String> busList = new ArrayList<String>();
				for (BuDTO bu : listOfBuDTO) {
					busList.add(bu.getBu_name());
				}
				
				List<UsersDTO> listOfUsersDTO = userService.getUsers();
				
				// Adding data to list from RolesDTO
				List<String> usersList = new ArrayList<String>();
				for (UsersDTO user : listOfUsersDTO) {
					usersList.add(user.getRealname());
				}
				populateUsersListInJson(usersList);
				
				List<String> jobtype=Arrays.asList(str_jobtype.split(","));
				List<String> recordtype=Arrays.asList(str_recordtype.split(","));
				List<String> status=Arrays.asList(str_status.split(","));
				List<String> shift=Arrays.asList(str_shift.split(","));
				List<String> priority=Arrays.asList(str_priority.split(","));
				List<String> done_percentage=Arrays.asList(str_done_percentage.split(","));
				
				AssignTaskDailylogDTO atdl = new AssignTaskDailylogDTO();
			
				model.addAttribute("atdl", atdl);
				model.addAttribute("jobtype", jobtype);
				model.addAttribute("recordtype", recordtype);	
				model.addAttribute("status", status);
				model.addAttribute("shift", shift);
				model.addAttribute("priority", priority);
				model.addAttribute("done_percentage", done_percentage);
				model.addAttribute("busList", busList);
				model.addAttribute("usersList", usersList);
				
				return "addtask";
			} 
		}
		else{

			if(assignTaskDTO!=null && dailyLogDTO!=null){
					assignTaskService.addAssignTask(assignTaskDTO, dailyLogDTO);
					return "assigntasklist";				
			}					
		}
		return "addtask";
}
	 private void populateUsersListInJson(List<String> usersList)throws IOException  {

				
				ResourceLoader resourceLoader = new FileSystemResourceLoader();
				Resource resource = resourceLoader
						.getResource(applicationProperties.getProperty(Constants.JSON_FILES.realname_filename.name()));
				String list = CommonUtilities.convertToJSON(usersList);

				try {
					WriteJSONToFile.getInstance().write(resource, list);
				} catch (IOException e) {
				System.out.println("IOEXCEPTION --- e"+e);
					e.printStackTrace();
				}
	
	}

}
