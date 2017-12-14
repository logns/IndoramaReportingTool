package com.lognsys.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.exolab.castor.types.DateTime;
import org.jfree.chart.axis.DateTickUnit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UpdatedbyDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.exception.CustomGenericException;
import com.lognsys.model.DailyLog;
import com.lognsys.model.UpdateAssignedCount;
import com.lognsys.service.AssignTaskService;
import com.lognsys.service.DailyLogService;
import com.lognsys.service.UserService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.DateTimeUtils;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;


@Controller
public class AssignTaskController {
	
	@Autowired
	AssignTaskService assignTaskService;

	@Autowired
	DailyLogService dailyLogService;
	
	@Autowired
	UserService userService;
	
	List<UsersDTO> data=null;


	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	/**
	 * SHOW ASSINGTASKLIST by reading the data
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/assigntasklist", method = RequestMethod.GET)
	public String showAssignTasks(Model model, HttpServletRequest request) {
		try {
			
			assignTaskService.readAssignTask();
		} catch (IOException e) {
			System.out.println("\n IOException showAssignTasks assigntasklist \n \n " +e.toString());
			throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
		}
		catch (Exception e) {
			System.out.println("\n Exception showAssignTasks assigntasklist \n \n " +e.toString());
//			customgenericException class for the exception  to  be viewed in web  page
			throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
		}
		return "assigntasklist";
	}
	
//	@ExceptionHandler to decide which “view” should be returned back if certain exception is raised
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		
		ModelAndView model = new ModelAndView("generic_error");
		model.addObject("errMsg", ex.getErrMsg());

		System.out.println("\n CustomGenericException handleCustomException assigntasklist  ex \n \n " +ex.toString());
		System.out.println("\n CustomGenericException handleCustomException assigntasklist  model \n \n " +model);
		return model;

	}

//	all kind of exception  is shown via this method
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		System.out.println("\n CustomGenericException handleAllException  assigntasklist ex \n \n " +ex.toString());
		
		ModelAndView model = new ModelAndView("generic_error");
		model.addObject("errMsg", "this is Exception.class");
		
		System.out.println("\n CustomGenericException handleAllException assigntasklist model \n \n " +model);
		
		
		return model;

	}
	/**
	 * Add ASSIGNTASKS
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
		
		// Adding data to list from BuDTO
		List<String> busList = new ArrayList<String>();
		for (BuDTO bu : listOfBuDTO) {
			busList.add(bu.getBu_name());
		}
		
		List<UsersDTO> listOfUsersDTO = userService.getUsers();
		
		// Adding data to list from UsersDTO
		List<String> usersList = new ArrayList<String>();
		for (UsersDTO user : listOfUsersDTO) {
			usersList.add(user.getRealname());
		}
//		populate userlist in realname.json for autocompleteview  
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
	 * SAVE data to database
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("atdl") AssignTaskDailylogDTO atdldto, BindingResult result, ModelMap model) throws IOException {
		boolean error=false;
		
//		validating
		FormValidator formValidator = new FormValidator();
		formValidator.validate(atdldto, result);
		
//		checking isExist
		boolean isexist=assignTaskService.isexist(atdldto.getAssignTaskDTO().getTitle());

		if(result.hasErrors() || isexist) {
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

			// Adding data to list from UsersDTO
			List<UsersDTO> listOfUsersDTO = userService.getUsers();
				
			List<String> usersList = new ArrayList<String>();
			for (UsersDTO user : listOfUsersDTO) {
				usersList.add(user.getRealname());
			}
//			populating list in json file for realname
			populateUsersListInJson(usersList);
				
			List<String> jobtype=Arrays.asList(str_jobtype.split(","));
			List<String> recordtype=Arrays.asList(str_recordtype.split(","));
			List<String> status=Arrays.asList(str_status.split(","));
			List<String> shift=Arrays.asList(str_shift.split(","));
			List<String> priority=Arrays.asList(str_priority.split(","));
			List<String> done_percentage=Arrays.asList(str_done_percentage.split(","));
				
			model.addAttribute("jobtype", jobtype);
			model.addAttribute("recordtype", recordtype);	
			model.addAttribute("status", status);
			model.addAttribute("shift", shift);
			model.addAttribute("priority", priority);
			model.addAttribute("done_percentage", done_percentage);
			model.addAttribute("busList", busList);
			model.addAttribute("usersList", usersList);
			
			if(isexist==true){
				error=isexist;
				model.addAttribute("message", "Title already Exist !!");
			}
			return "addtask";
		} 
		else{
			try {
				AssignTaskDTO assignTaskDTO=atdldto.getAssignTaskDTO();
				DailyLogDTO dailyLogDTO=atdldto.getDailylogDTO();

				dailyLogDTO.setAssign_task_title(assignTaskDTO.getTitle());
				dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date());
				dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage());
				
//				add  assigntask  and dailylog
				assignTaskService.addAssignTask(assignTaskDTO, dailyLogDTO);
				
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n DataAccessException saveForm \n \n " +e.toString());
				throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.data_access_exception.name()));	
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("\n IOException saveForm \n \n " +e.toString());
				throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
			}
		}
		return "assigntasklist";
	}
	 private void populateUsersListInJson(List<String> usersList)throws IOException  {				
				ResourceLoader resourceLoader = new FileSystemResourceLoader();
				Resource resource = resourceLoader
						.getResource(applicationProperties.getProperty(Constants.JSON_FILES.realname_filename.name()));
				String list = CommonUtilities.convertToJSON(usersList);

				try {
					WriteJSONToFile.getInstance().write(resource, list);
				} catch (IOException e) {
//				System.out.println("IOEXCEPTION --- e"+e);
					e.printStackTrace();
					System.out.println("\n IOException saveForm \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
				}
	
	}
	 /**
		 * MANAGETASK handles DELETE and EDIT via @RequestParam taskIds, taskAction
		 * @param model
		 * @param taskIds
		 * @param taskAction
		 * @return
	 * @throws IOException 
		 */
		@RequestMapping(value = "/assigntasklist", method = RequestMethod.POST)
		public String manageTask(Model model, @RequestParam(value = "taskIds", required = false) String taskIds,
				 @RequestParam String taskAction) throws IOException {
	switch (taskAction) {
			case "delete":
				JSONParser parser = new JSONParser();
				try {
					Object obj = parser.parse(taskIds);

					JSONArray arr = (JSONArray) obj;
					System.out.println("\n \n manageTask arr===== "+arr.toJSONString());
					
					String[] titles = new String[arr.size()];
					for (int i = 0; i < arr.size(); i++) {

						JSONObject jsonObject = (JSONObject) arr.get(i);
						System.out.println("\n \n manageTask jsonObject===== "+jsonObject.toJSONString());
						
						titles[i] = Jsoup.parse(jsonObject.get("title").toString()).text();
						System.out.println("\n \n manageTask titles[i] ===== "+titles[i] );
						
					}
					assignTaskService.deleteTaskTitle(titles);
					assignTaskService.readAssignTask();
				} catch (DataAccessException e) {
					e.printStackTrace();
					System.out.println("\n DataAccessException delete manageTask \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.data_access_exception_delete.name()));	
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("\n ParseException manageTask delete \n \n " +e.toString());
					throw new CustomGenericException("This is manageTask ParseException message");	
			
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("\n IOException manageTask \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
			
				}
				return "assigntasklist";
			case "edit":

				JSONParser p = new JSONParser();
				try {
					Object obj = p.parse(taskIds);
					JSONArray arr = (JSONArray) obj;
					String id = "";
					for (int i = 0; i < arr.size(); i++) {

						JSONObject jsonObject = (JSONObject) arr.get(i);
						id = jsonObject.get("id").toString();
					}

					AssignTaskDTO assignTaskDTO=assignTaskService.getAssigntaskDTOById(Integer.parseInt(id));
					assignTaskDTO.setId(Integer.parseInt(id));
					
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
					atdl.setAssignTaskDTO(assignTaskDTO);
					
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
				}catch (DataAccessException e) {
					e.printStackTrace();
					System.out.println("\n DataAccessException edit manageTask \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.data_access_exception_edit.name()));	
				}  catch (ParseException e) {
					e.printStackTrace();
					System.out.println("\n IOException manageTask \n \n " +e.toString());
					throw new CustomGenericException("This is manageTask IOException message");	
				}catch (IOException e) {
					e.printStackTrace();
					System.out.println("\n IOException manageTask \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
				}
			}
			return "assigntasklist";
		}
		
		 /**
		 * UPDATE  task details 
		 *  via @ModelAttribute editassigndailylog
		 * @param model
		 * @param taskIds
		 * @param taskAction
		 * @return
	 * @throws IOException 
		 */
// 		updating 
		@RequestMapping(value = { "/editassigndailylog" }, method = RequestMethod.POST)
		public String editAssignDailyLog(@ModelAttribute("editassigndailylog") AssignTaskDailylogDTO assignTaskDailylogDTO,BindingResult result,Model model) throws IOException {
			try {
				boolean isDailyLogUpdate=false;
				assignTaskService.updateAssigntask(assignTaskDailylogDTO,isDailyLogUpdate);
				return "assigntasklist";
			} catch (Exception e) {
				System.out.println("\n Exception editAssignDailyLog \n \n " +e.toString());
				throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
			}
		}
		

		 /**
		 * UPDATEASSIGNED_DAILYLOG   details 
		 *  via /taskdetailview
		 * @param jsonData
		 * @return
	 * @throws IOException 
		 */
		@RequestMapping(value = { "/taskdetailview" }, method = RequestMethod.POST)
		public @ResponseBody String updateAssigned_DailyLog(@RequestBody String jsonData, HttpServletRequest request) throws IOException {
			try {
				JSONParser parser = new JSONParser();
				try {
//					JSON parsing
					Object obj = parser.parse(jsonData);
					System.out.println("\n updateAssigned_DailyLog taskdetailview obj ------------------------ " +obj.toString());
					
					JSONObject jsonObject = (JSONObject)obj;
					
//					getting assigntask  and dailylog parsed data in ASSIGNTASKDAILYLOG_DTO
					AssignTaskDailylogDTO assignTaskDailylogDTO=assignTaskService.parseJsonToAssigntaskDailyLogDTO(jsonObject);
				
					System.out.println("\n updateAssigned_DailyLog taskdetailview obj ------------------------ " +assignTaskDailylogDTO.getAssignTaskDTO().toString());
					System.out.println("\n updateAssigned_DailyLog taskdetailview obj ------------------------ " +assignTaskDailylogDTO.getDailylogDTO().toString());

//					This flag says when we update dailylog with assigntask  than set true else if we update assigntask  by editassigndailylog than  no daily log will  update
					boolean isDailyLogUpdate=true;
					assignTaskService.updateAssigntask(assignTaskDailylogDTO,isDailyLogUpdate);

					if(assignTaskDailylogDTO.getDailylogDTO().getStatus().equalsIgnoreCase("closed") && assignTaskDailylogDTO.getDailylogDTO().getDone_percentage().equalsIgnoreCase("100%")){
						
						dailyLogService.deleteDailyLogs(assignTaskDailylogDTO.getDailylogDTO().getAssign_task_id());	
					}
				}
				catch (ParseException e) {
					System.out.println("\n updateAssigned_DailyLog taskdetailview ParseException ------------------------ " +e.toString());
				}
				return "taskdetailview";
			} catch (Exception e) {
				System.out.println("\n Exception /taskdetailview \n \n " +e.toString());
				throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
			}
		}

		 /**
		 * show Task with  dailylog when  clicked on assigntask  TITLE via assigntasklist form 
		 *   @RequestParam("title") String title
		 *   @RequestParam("assign_task_id") int assign_task_id
		 *   @RequestParam(value = "bottom_id", required = false) String bottom_id will  give us the exact view or container  which  we open and close (toggle)
		 * @param jsonData
		 * @return
	 * @throws IOException 
		 */
		 @RequestMapping(value = "/taskdetailview", method = RequestMethod.GET)
			public String showList(@RequestParam("title") String title,@RequestParam("assign_task_id") int assign_task_id,
					Model model, HttpServletRequest request,
					@RequestParam(value = "bottom_id", required = false) String bottom_id)
					throws IOException {
			 try {
					title =title.replace("%20", " ");
//					extracting title of assigntask
						AssignTaskDTO assignTaskDTO=assignTaskService.getAssigntDTObyTitle(title);
					
//						getting dailylog list at first
						List<DailyLogDTO> list=dailyLogService.fetchDailyLog(title);
						
//						this list is when user click on list.item  this list populate item in it when user click  on submit
						List<DailyLogDTO> dailyLogDTOs=new ArrayList<DailyLogDTO>();
											
						if(list!= null && list.size()>0){ 
							for(int i=0;i<list.size();i++){
								
								DateTimeUtils dateTimeUtils=new DateTimeUtils();
								String strDate=dateTimeUtils.printDifference(list.get(i).getLast_edit(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
								
								DailyLogDTO dailylogDTO= list.get(i);
								
								dailylogDTO.setAssigned_to("Updated by \t \t"+assignTaskDTO.getAssigned_to());
								dailylogDTO.setLast_edit(strDate);
								System.out.println("\n showList taskdetailview dailylogDTO ------------------------ " +dailylogDTO.toString());
								dailyLogDTOs.add(dailylogDTO);
							}
							System.out.println("\n showList taskdetailview dailyLogDTOs size------------------------ " +dailyLogDTOs.size());
						}
						AssignTaskDailylogDTO atdl = new AssignTaskDailylogDTO();
						
//						this bottom_id will  get dailylog id append with  bottom keywork(eg. bottom_111 ) when  we slide the toggle
						if(bottom_id!=null){
							dailyLogDTOs=new ArrayList<DailyLogDTO>();
							String[] array=bottom_id.split("_");
							
//							extacting the id
							int id=Integer.parseInt(array[1]);

//							getting dailylog by id from dailylogservice
							DailyLogDTO dailylogDTO =dailyLogService.getDailLogbyId(id);
							dailylogDTO.setAssign_task_title(assignTaskDTO.getTitle());
							dailylogDTO.setAssigned_to(assignTaskDTO.getAssigned_to());
						
//							adding in arraylist
							dailyLogDTOs.add(dailylogDTO);
							System.out.println("\n showList taskdetailview BOTTOM_ID dailyLogDTOs size " +dailyLogDTOs.size());

//							adding in modelattribute
							atdl.setDailylogDTO(dailylogDTO);
						}

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
						
						atdl.setAssignTaskDTO(assignTaskDTO);
//						sending to jstl  form
						atdl.setDailyLogDTOs((ArrayList<DailyLogDTO>) dailyLogDTOs);
						model.addAttribute("atdl", atdl);
						model.addAttribute("jobtype", jobtype);
						model.addAttribute("recordtype", recordtype);	
						model.addAttribute("status", status);
						model.addAttribute("shift", shift);
						model.addAttribute("priority", priority);
						model.addAttribute("done_percentage", done_percentage);
						model.addAttribute("busList", busList);
						model.addAttribute("usersList", usersList);
						model.addAttribute("dailyLogDTOs", dailyLogDTOs);

					return "taskdetailview";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("\n Exception showList \n \n " +e.toString());
					throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
				
				}
			}
}