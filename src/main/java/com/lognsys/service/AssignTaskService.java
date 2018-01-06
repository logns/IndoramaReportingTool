package com.lognsys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.exolab.castor.types.Date;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.DailyLogBuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.AssignTask;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.AssignTaskService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("assigntaskService")
public class AssignTaskService {


	@Autowired
	private JdbcUserRepository jdbcUserRepository;
	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;

	@Autowired
	private JdbcBuRepository jdbcBuRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;


	@Qualifier("messagesProperties")
	private Properties messagesProperties;
	
	@Autowired
	private MessageSource msg;

	DailyLogDTO dailyLogDTO;
	AssignTaskDTO assignTaskDTO;
	List<AssignTaskDTO> listOfAssigntask;
	
	String message;
	@Autowired
	MailService mailService;

	Authentication  authentication;
	/**
	 * Add assignTaskDTO,dailyLogDTO to database..
	 *
	 * DAILYLOGS CAN HAVE ONLY ONE ASSIGNTASK 
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addAssignTask(AssignTaskDTO assignTaskDTO, DailyLogDTO dailyLogDTO) throws IOException {
	
//		Adding assigntask in db
		int assign_task_id = jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
		assignTaskDTO.setId(assign_task_id);

//		before adding dailylog set the assigntask id
		dailyLogDTO.setAssign_task_id(assign_task_id);

//		adding dailylog
		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		
//		DAILYLOG_USERS_QUERIES
	    jdbcDailyLogRepository.addDailylogUsers(ObjectMapper.authorizedUserName(),dailylog_id);
			
//		adding both the tables id in assign_dailylog tables 
		int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assign_task_id, dailylog_id);
		
		
		
//		adding data to dailylog_bu
		if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
			int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
			
			if (bu_id > 0 && bu_id != 0) {
				jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
			}
		}
		try {
//			reading assigntask
			authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("addTask false user role - "+(authentication.getPrincipal().toString()));
			
			if (authentication.getPrincipal().toString().equalsIgnoreCase("ADMIN")) {
				readAssignTask(null);
			}
			else{
				if(ObjectMapper.authorizedUserName()!=null){
				readAssignTask(ObjectMapper.authorizedUserName());
				}
			}
			
			String message = msg.getMessage("addnewtask", new Object[] {assignTaskDTO.getAssigned_to(),ObjectMapper.authorizedUserName(), "http://www.mkyong.com",dailyLogDTO.getDescription()}, null);
//			processing mail 
			processMail(dailyLogDTO,message);
		} catch (IOException io) {
			System.out.println("Rest addAssignTask  readAssignTask - " + io.getMessage());
		}
		return assign_task_id;
	}

	private void processMail(DailyLogDTO dailyLogDTO,String message) {

		List<UsersDTO> listOfUsersDTO = jdbcUserRepository.getAllUsers();
		if(listOfUsersDTO!=null && listOfUsersDTO.size()>0){

			authentication = SecurityContextHolder.getContext().getAuthentication();
				String username=authentication.getName().toString();
				System.out.println("\n  processMail username " +username);
				System.out.println("\n  processMail dailyLogDTO " +dailyLogDTO.toString());
	
				if(username!=null && dailyLogDTO!=null){
				
					mailService.processData(dailyLogDTO,username,message);
				}
		}
	}

	//	checking isexist
	public boolean isexist(String title) {
		return jdbcAssignTaskRepository.isexist(title);
	}

	public void readAssignTask(String username) throws IOException {
		System.out.println("readAssignTask username - "+(username));
		
		if(username!=null){
			listOfAssigntask = jdbcAssignTaskRepository.getAllAssignTaskDTOByUsername(username);
		}
		else{
//				getting list of assigntask 
			listOfAssigntask = jdbcAssignTaskRepository.getAllAssignTaskDTO();
			
		}

		List<AssignTaskTable> assignTaskTables = null;
		
		// adding the list of assigntask into  assigntasktable to  show in UI
		if (listOfAssigntask != null && listOfAssigntask.size() > 0) {
			assignTaskTables = ObjectMapper.mapToAssignTaskTable(listOfAssigntask);
		}

		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.assigntask_filename.name()));
		String list = CommonUtilities.convertToJSON(assignTaskTables);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
				
			
		} catch (IOException e) {
			System.out.println("readAssignTask IOEXCEPTION --- e" + e);
			e.printStackTrace();
		}
	}
	
//		REMOVING ASSIGNTASK DATA FROM DATABASE	
	public int removeAssignTask(int[] ids) throws IOException {
		
		for (int id : ids) {
			try {

				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOBy(id);

				if (!isDelete) {
					return 0;
				} else {
//					reading assigntask
					authentication = SecurityContextHolder.getContext().getAuthentication();
					if (authentication.getPrincipal().toString().equalsIgnoreCase("ADMIN")) {
						readAssignTask(null);
					}
					else{
						if(ObjectMapper.authorizedUserName()!=null){
						readAssignTask(ObjectMapper.authorizedUserName());
						}
					}
					
				}
			} catch (DataAccessException | IOException dae) {

				// LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}

		}
		return 1;
	}

	/**
	 * Delete ASSIGNTASK from database
	 * 
	 * @param String
	 *            titles[]
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */
	public int deleteTaskTitle(String[] titles) throws IOException {
		for (String title : titles) {
			try {
				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOByTitle(title);

				if (!isDelete) {
					return 0;
				} else {
//					reading assigntask
					authentication = SecurityContextHolder.getContext().getAuthentication();
					if (authentication.getPrincipal().toString().equalsIgnoreCase("ADMIN")) {
						readAssignTask(null);
					}
					else{
						if(ObjectMapper.authorizedUserName()!=null){
						readAssignTask(ObjectMapper.authorizedUserName());
						}
					}
					
				}

				
				String message = msg.getMessage("removedtask", new Object[]{authentication.getName().toString()}, null);
				//String message = msg.getProperty(Constants.MESSAGES_PROPERTIES.removedtask.name());
				
				processMail(dailyLogDTO,message);
			} catch (DataAccessException dae) {
				throw new IllegalStateException("Error : Failed to delete task!");
			}
		}
		return 1;
	}

//	get ASSIGNTASKDTO BY ID
	public AssignTaskDTO getAssigntaskDTOById(int id) {
		AssignTaskDTO assignTaskDTO = jdbcAssignTaskRepository.findAssignTaskDTOId(id);
		return assignTaskDTO;
	}

	/**
	 * UPDATE ASSIGNTASK
	 * 
	 * @param assignTaskDailylogDTO
	 */
	@Transactional
	public boolean updateAssigntask(AssignTaskDailylogDTO assignTaskDailylogDTO,boolean isDailyLogUpdate) {
		boolean isUpdated = false;
		try {
			
			AssignTaskDTO assignTaskDTO=assignTaskDailylogDTO.getAssignTaskDTO();
			dailyLogDTO=assignTaskDailylogDTO.getDailylogDTO();
			
			AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(assignTaskDTO.getTitle());
			assignTaskDTO.setId(dto.getId());
			
//			UPDATING ASSIGNTASK
			isUpdated = jdbcAssignTaskRepository.updateAssignTaskDTO(assignTaskDTO);
			System.out.println("updateAssigntask isUpdated == "+isUpdated);
			
			
			dailyLogDTO.setAssign_task_id(assignTaskDTO.getId());
			dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage());
			dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date());
			if(isDailyLogUpdate){
//				UPDATE DAILYLOGDTO
				isUpdated =jdbcDailyLogRepository.updateDailyLogDTO(dailyLogDTO);
				System.out.println("updateDailyLogDTO isUpdated == "+isUpdated);

////				UPDATE data to dailylog_bu
				if (dailyLogDTO.getId() > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
				
					int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
					System.out.println("findBuByName bu_id == "+bu_id);
	
					DailyLogBuDTO buDTO=jdbcDailyLogRepository.findDailyLogDTOBuByDailyLogId(dailyLogDTO.getId());
					buDTO.getBuDTO().setId(bu_id);
					if (bu_id > 0 && bu_id != 0) {
						isUpdated =jdbcDailyLogRepository.updateBuOfDailyLogBu(buDTO);
						System.out.println("updateBuOfDailyLogBu isUpdated == "+isUpdated);

					}

					String message = msg.getMessage("updatetask",new Object[] {assignTaskDTO.getAssigned_to(),ObjectMapper.authorizedUserName(), "http://www.mkyong.com",dailyLogDTO.getDescription()}, null);
					processMail(dailyLogDTO,message);
				}
			}
			else{
//				ADDINGNEW DAILYLOGDTO
				int dailylog_id=jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
				System.out.println("updateAssigntask updateDailyLogDTO  " );

				int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assignTaskDTO.getId(), dailylog_id);
				System.out.println("updateAssigntask addDailylog assignDailyLog_id " + assignDailyLog_id);

//				adding data to dailylog_bu
				if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
					int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
					if (bu_id > 0 && bu_id != 0) {
						jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
						System.out.println("updateAssigntask addDailylog addDailyLogAndBu ");
					}
				}
			}
//			reading assigntask
			authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getPrincipal().toString().equalsIgnoreCase("ADMIN")) {
				readAssignTask(null);
			}
			else{
				if(ObjectMapper.authorizedUserName()!=null){
				readAssignTask(ObjectMapper.authorizedUserName());
				}
			}
			
		}
		catch (DataAccessException dae) {
		System.out.println("\n \n updateAssigntask DataAccessException == "+dae.toString()+"\n \n");

			throw new IllegalStateException("Failed  update Task status - " + isUpdated);
		} catch (IOException e) {
			System.out.println("\n \n updateAssigntask IOException == "+e.toString()+"\n \n");
			throw new IllegalStateException(e);
		}
		return isUpdated;
	}

	public AssignTaskDTO getAssigntDTObyTitle(String title){
		AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(title);
		System.out.println("\n getAssigntDTObyTitle  dto ------------------------ "
				+ dto.toString());

		return dto;
	}
	public static  AssignTaskDailylogDTO parseJsonToAssigntaskDailyLogDTO(JSONObject jsonObject) {
		AssignTaskDailylogDTO assignTaskDailylogDTO=new AssignTaskDailylogDTO();
		
		AssignTaskDTO assignTaskDTO=new AssignTaskDTO();
		int indexnumber=Integer.parseInt(jsonObject.get("indexnumber").toString());
		System.out.println("\n tasks indexnumber : "+indexnumber);
		
		JSONObject tasks=(JSONObject) jsonObject.get("taskIds");
		System.out.println("\n tasks : "+tasks);
		
		assignTaskDTO.setId(Integer.parseInt(tasks.get("assignTaskDTO.id").toString()));
		System.out.println("\n "+assignTaskDTO.getId());
		
		assignTaskDTO.setTarget_date(tasks.get("assignTaskDTO.target_date").toString());
		System.out.println("\n "+assignTaskDTO.getTarget_date());
		
		assignTaskDTO.setTitle(tasks.get("assignTaskDTO.title").toString());
		System.out.println("\n "+assignTaskDTO.getTitle());
		
		assignTaskDTO.setDone_percentage(tasks.get("assignTaskDTO.done_percentage").toString());
		System.out.println("\n "+assignTaskDTO.getDone_percentage());
		
		assignTaskDTO.setPriority(tasks.get("assignTaskDTO.priority").toString());
		System.out.println("\n "+assignTaskDTO.getPriority());
		
		assignTaskDTO.setAssigned_to(tasks.get("assignTaskDTO.assigned_to").toString());
		System.out.println("\n "+assignTaskDTO.getAssigned_to());
		
		
		
		DailyLogDTO dailyLogDTO=new DailyLogDTO();
		
		dailyLogDTO.setId(Integer.parseInt(tasks.get("dailyLogDTOs["+indexnumber+"].id").toString()));
		System.out.println("\n"+dailyLogDTO.getId());
		
		dailyLogDTO.setAssign_task_id(assignTaskDTO.getId());
		System.out.println("\n"+dailyLogDTO.getAssign_task_id());
		
		dailyLogDTO.setAssign_task_title(assignTaskDTO.getTitle().toString());
		System.out.println("\n "+dailyLogDTO.getAssign_task_title());
		
		dailyLogDTO.setShift(tasks.get("dailyLogDTOs["+indexnumber+"].shift").toString());
		System.out.println("\n "+dailyLogDTO.getShift());
		
		dailyLogDTO.setBu(tasks.get("dailyLogDTOs["+indexnumber+"].bu").toString());
		System.out.println("\n "+dailyLogDTO.getBu());
		
		dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage().toString());
		System.out.println("\n "+dailyLogDTO.getDone_percentage());
		
		dailyLogDTO.setSpare_parts(tasks.get("dailyLogDTOs["+indexnumber+"].spare_parts").toString());
		System.out.println("\n"+dailyLogDTO.getSpare_parts());
		
		dailyLogDTO.setTimefrom(tasks.get("dailyLogDTOs["+indexnumber+"].timefrom").toString());
		System.out.println("\n"+dailyLogDTO.getTimefrom());
		
		dailyLogDTO.setTimeto(tasks.get("dailyLogDTOs["+indexnumber+"].timeto").toString());
		System.out.println("\n"+dailyLogDTO.getTimeto());
		
		dailyLogDTO.setMachine(tasks.get("dailyLogDTOs["+indexnumber+"].machine").toString());
		System.out.println("\n"+dailyLogDTO.getMachine());
		
		dailyLogDTO.setDescription(tasks.get("dailyLogDTOs["+indexnumber+"].description").toString());
		System.out.println("\n"+dailyLogDTO.getDescription());
		
		dailyLogDTO.setAttendby(tasks.get("dailyLogDTOs["+indexnumber+"].attendby").toString());
		System.out.println("\n"+dailyLogDTO.getAttendby());
		
		dailyLogDTO.setJobtype(tasks.get("dailyLogDTOs["+indexnumber+"].jobtype").toString());
		System.out.println("\n"+dailyLogDTO.getJobtype());
		
		dailyLogDTO.setRecordtype(tasks.get("dailyLogDTOs["+indexnumber+"].recordtype").toString());
		System.out.println("\n"+dailyLogDTO.getRecordtype());
		
		dailyLogDTO.setStatus(tasks.get("dailyLogDTOs["+indexnumber+"].status").toString());
		System.out.println("\n"+dailyLogDTO.getStatus());
		
		dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date().toString());
		System.out.println("\n "+dailyLogDTO.getTarget_date());
		
		assignTaskDailylogDTO.setAssignTaskDTO(assignTaskDTO);
		assignTaskDailylogDTO.setDailylogDTO(dailyLogDTO);
		
		return assignTaskDailylogDTO;
	}
	
	public void displayDashboardAssignTask(String username) throws IOException {
		System.out.println("\n displayDashboardAssignTask username --- " + username);
		
		if(username!=null){
			listOfAssigntask = jdbcAssignTaskRepository.getDashboardAssignTask(username);
		}else{
			listOfAssigntask = jdbcAssignTaskRepository.getAllDashboardAssignTaskDTO();
		}
		
		List<AssignTaskTable> assignTaskTables = null;
		
		// adding the list of assigntask into  assigntasktable to  show in UI
		if (listOfAssigntask != null && listOfAssigntask.size() > 0) {
			assignTaskTables = ObjectMapper.mapToAssignTaskTable(listOfAssigntask);
			System.out.println("displayDashboardAssignTask listOfAssigntask size--- " + listOfAssigntask.size());
			
		
		System.out.println("displayDashboardAssignTask assignTaskTables size--- " + assignTaskTables.size());
		
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.dashboard_filename.name()));
		String list = CommonUtilities.convertToJSON(assignTaskTables);

			try {
				WriteJSONToFile.getInstance().write(resource, list);
					
				
			} catch (IOException e) {
				System.out.println("displayDashboardAssignTask IOEXCEPTION --- e" + e);
				e.printStackTrace();
			}
		}
	}
	

}
