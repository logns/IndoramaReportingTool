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

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersBuDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.AssignTask;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;

public class ObjectMapper {


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
	public static List<UsersTable> mapToUserTable(List<UsersBuDTO> userbudto) {
		List<UsersTable> list = new ArrayList<>();
		for (UsersBuDTO ubu : userbudto) {
			list.add(new UsersTable(ubu.getUser().getId(), ubu.getUser().getRealname(), ubu.getUser().getUsername(),
					ubu.getBuDTO().getBu_name(), ubu.getUser().isEnabled()));
		}
		return list;
	}
	public static List<AssignTaskTable> mapToAssignTaskTable(List<AssignTaskDTO> assignTaskTables) {
		List<AssignTaskTable> list = new ArrayList<>();
		for (AssignTaskDTO ubu : assignTaskTables) {
			list.add(new AssignTaskTable(ubu.getId(),convertToAnchorTagString(ubu.getTitle()), ubu.getAssigned_to(),
					ubu.getPriority(), ubu.getTarget_date(),ubu.getDone_percentage()));
		}
		return list;
	}
public static String convertToAnchorTagString(String title){
	String titles="<a href="+"http://localhost:8080/dailyloglist?title="+title+" >"+title+"</a>";
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
		dldto.setAssign_task_title(dailyLog.getAssign_task_title());
		dldto.setTarget_date(dailyLog.getTarget_date());
		dldto.setShift(dailyLog.getShift());
		dldto.setMachine(dailyLog.getMachine());
		dldto.setDescription(dailyLog.getDescription());
		dldto.setTimefrom(dailyLog.getTimefrom());
		dldto.setTimeto(dailyLog.getTimeto());
		dldto.setSpare_parts(dailyLog.getSpare_parts());
		dldto.setAttendby(dailyLog.getAttendby());
		dldto.setJobtype(dailyLog.getJobtype());
		dldto.setRecordtype(dailyLog.getRecordtype());
		dldto.setStatus(dailyLog.getStatus());
		dldto.setDone_percentage(dailyLog.getDone_percentage());
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

		Users newusers = new Users(users.getId(),users.getUsername(), users.getRealname(),
				users.getPhone(), users.getBirthdate(), users.isEnabled(),
				users.isNotification(), users.getAddress(), users.getCity(), users.getState(),
				users.getZipcode(),firstname,lastname);

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
