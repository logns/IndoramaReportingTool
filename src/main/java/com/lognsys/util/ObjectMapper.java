package com.lognsys.util;

import java.io.IOException;

/**
 * @author pdoshi
 * 
 * Description : 
 * ObjectMapper class is used to map POJO from model to Data Transfer Object DTO
 * POJO in model in directory is used with frontend and validation which needs to be mapped to corresponding DTO
 * where fields are identical to the mysql database. 
 * 
 * CHANGE LOG:
 * PJD - 23/05/17 : Changing the setting of fields using constructor to setter method.
 * 
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersBuDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.AssignTask;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.DailylogTable;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;

public class ObjectMapper {
	public static String authorizedUserName(){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		UserDetails ud = (UserDetails) ((Authentication)loggedInUser).getPrincipal();
		return  ud.getUsername();
	}
	

	public static AssignTaskDTO mapToAssignTaskDTO(AssignTask assignTask) {

		AssignTaskDTO atdto = new AssignTaskDTO();
		atdto.setTitle(assignTask.getTitle());
		atdto.setAssigned_to(assignTask.getAssigned_to());
		atdto.setPriority(assignTask.getPriority());
		atdto.setTarget_date(assignTask.getTarget_date());
		atdto.setDone_percentage(assignTask.getDone_percentage());
		return atdto;
	}

	/**
	 * Based on the group map all the usersDTO object to Users Tables object
	 * 
	 * @param groups
	 * @return
	 */
	public static List<UsersTable> mapToUserTable(List<UsersDTO> userbudto) {
		List<UsersTable> list = new ArrayList<>();
		for (UsersDTO ubu : userbudto) {
			list.add(new UsersTable(ubu.getId(), ubu.getRealname(), ubu.getUsername(),
					 ubu.isEnabled()));
		}
		return list;
	}

	public static List<AssignTaskTable> mapToAssignTaskTable(List<AssignTaskDTO> assignTaskTables) {
		List<AssignTaskTable> list = new ArrayList<>();
		for (AssignTaskDTO atdto : assignTaskTables) {
			AssignTaskTable at=new AssignTaskTable();
			
			at.setId(atdto.getId());
			if(atdto.getTitle()!=null){
				at.setTitle(convertToAnchorTagString(atdto.getTitle(),atdto.getId()));
			}
			if(atdto.getCreated_by()!=null){
				at.setCreated_by(atdto.getCreated_by());
			}
			if(atdto.getStatus()!=null){
				at.setStatus(atdto.getStatus());
			}
			if(atdto.getPriority()!=null){
				at.setPriority(atdto.getPriority());
			}
			if(atdto.getTarget_date()!=null){
				at.setTarget_date(atdto.getTarget_date());
			}
			if(atdto.getDone_percentage()!=null){
				at.setDone_percentage(atdto.getDone_percentage());
			}
			if(atdto.getAssigned_to()!=null){
				at.setAssigned_to(atdto.getAssigned_to());
			}
			list.add(at);
		}
		return list;
	}

	public static List<DailylogTable> mapToDailyLogTable(List<DailyLogDTO> dailyLogDTOs) {
		List<DailylogTable> list = new ArrayList<>();
		for (DailyLogDTO ubu : dailyLogDTOs) {
			
			list.add(new DailylogTable(ubu.getId(), ubu.getAssign_task_id(), ubu.getAssign_task_title(),
					convertToAnchorTagDescription(ubu.getDescription(),ubu.getId(),ubu.getAssign_task_title()), ubu.getAssigned_to(), ubu.getTarget_date(),
					ubu.getDone_percentage(), ubu.getStatus(),ubu.getLast_edit(),ubu.getBu()));
		}
		return list;
	}

	public static String convertToAnchorTagDescription(String description, int id,String assign_task_title) {

		String descriptions=null;
		if(assign_task_title!=null && description!=null){
			if(description.contains(" ") || assign_task_title.contains(" ")) {
				 descriptions = "<a href=" + "http://localhost:8080/adddailylog?description=" + description.replace(" ", "%20") + "&id="+id+ "&assign_task_title="+assign_task_title.replace(" ", "%20") +" >"
							+ description + "</a>";
			}
			else{
				 descriptions = "<a href=" + "http://localhost:8080/adddailylog?description=" +description+ "&id="+id + "&assign_task_title="+assign_task_title+" >"

							+ description + "</a>";

			}
			return descriptions;
			
		}
		else
		System.out.println("convertToAnchorTagDescription ======= descriptions ===============" + descriptions + "\n\n\n");
		return descriptions;
	}


	public static String convertToAnchorTagString(String title,int assign_task_id) {
	String titles = "<a href=" + "http://localhost:8080/taskdetailview?title=" + title.replace(" ", "%20") +"&assign_task_id="+assign_task_id+ " >"
				+ title + "</a>";
	return titles;
	}

	/**
	 * 
	 * Map POJO Users Object to Users DTO Object
	 * 
	 * @param users
	 * @return
	 */
	public static UsersDTO mapToUsersDTO(Users users) {

		UsersDTO uDTO = new UsersDTO();

		uDTO.setId(users.getId());
		uDTO.setRealname(users.getFirstname(), users.getLastname());
		uDTO.setUsername(users.getUsername());
		uDTO.setPhone(users.getPhone());
		uDTO.setBirthdate(users.getBirthdate());
		uDTO.setEnabled(users.isEnabled());
		uDTO.setNotification(users.isNotification());
		uDTO.setAddress(users.getAddress());
		uDTO.setCity(users.getCity());
		uDTO.setState(users.getState());
		uDTO.setZipcode(users.getZipcode());
		uDTO.setPassword(users.getPassword());

		return uDTO;

	}

	/**
	 * 
	 * Map POJO Users Object to Users DTO Object
	 * 
	 * @param users
	 * @return
	 */
	public static DailyLogDTO mapToDailyLogDTO(DailyLog dailyLog) {

		DailyLogDTO dldto = new DailyLogDTO();
		dldto.setAssign_task_id(dailyLog.getAssign_task_id());
		dldto.setAssign_task_title(dailyLog.getAssign_task_title());
		dldto.setTarget_date(dailyLog.getTarget_date());
		dldto.setShift(dailyLog.getShift());
		dldto.setMachine(dailyLog.getMachine());
		dldto.setDescription(dailyLog.getDescription());
		dldto.setTimefrom(dailyLog.getTimefrom());
		dldto.setTimeto(dailyLog.getTimeto());
		dldto.setSpare_parts(dailyLog.getSpareparts());
		dldto.setAttendby(dailyLog.getAttendby());
		dldto.setJobtype(dailyLog.getJobtype());
		dldto.setRecordtype(dailyLog.getRecordtype());
		dldto.setStatus(dailyLog.getStatus());
		dldto.setDone_percentage(dailyLog.getDone_percentage());
		dldto.setBu(dailyLog.getBu());
		return dldto;

	}
	public static DailyLog mapToDailyLog(DailyLogDTO dailyLogDTO) {

		DailyLog dldto = new DailyLog();
		dldto.setAssign_task_id(dailyLogDTO.getAssign_task_id());
		dldto.setAssign_task_title(dailyLogDTO.getAssign_task_title());
		dldto.setTarget_date(dailyLogDTO.getTarget_date());
		dldto.setShift(dailyLogDTO.getShift());
		dldto.setMachine(dailyLogDTO.getMachine());
		dldto.setDescription(dailyLogDTO.getDescription());
		dldto.setTimefrom(dailyLogDTO.getTimefrom());
		dldto.setTimeto(dailyLogDTO.getTimeto());
		dldto.setSpareparts(dailyLogDTO.getSpare_parts());
		dldto.setAttendby(dailyLogDTO.getAttendby());
		dldto.setJobtype(dailyLogDTO.getJobtype());
		dldto.setRecordtype(dailyLogDTO.getRecordtype());
		dldto.setStatus(dailyLogDTO.getStatus());
		dldto.setDone_percentage(dailyLogDTO.getDone_percentage());
		dldto.setBu(dailyLogDTO.getBu());
		return dldto;

	}
	/**
	 * Map UsersDTO with Users object in model directory
	 * 
	 * NOTE: Sync Users.java fields with this method
	 * 
	 * @param users
	 * @return
	 */
	public static Users mapToUsers(UsersDTO users) {
		// TODO: Current setting of group to null, but need to change to value

		String[] splited = null;
		String firstname = "", lastname = "";

		splited = CommonUtilities.splitByDelemeter(users.getRealname(), " ");

		if (splited == null) {
			firstname = "";
			lastname = "";
		}

		if (splited.length == 1) {
			firstname = splited[0];
			lastname = "";
		}

		if (splited.length == 2) {
			firstname = splited[0];
			lastname = splited[1];
		}

		Users newusers = new Users(users.getId(), users.getUsername(), users.getRealname(), users.getPhone(),
				users.getBirthdate(), users.isEnabled(), users.isNotification(), users.getAddress(), users.getCity(),
				users.getState(), users.getZipcode(), firstname, lastname);

		return newusers;

	}

	/**
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Users convertJSONtoObject(String jsonStr) {
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		Users users = null;
		try {
			users = mapper.readValue(jsonStr, Users.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}


}
