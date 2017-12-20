package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;

public interface AssignTaskRepository {

	/**
	 * Add assignTaskDTO into database
	 * 
	 * @param assignTaskDTO
	 */
	public int addAssignTask(AssignTaskDTO assignTaskDTO);
	
	/**
	 * Add AssignTask_DailyLog into database
	 * 
	 * @param assignTask_id, dailylog_id
	 */
	public int addAssignTask_DailyLog(int assignTask_id,int dailylog_id);
	
	
	/**
	 *check title isExits 
	 * 
	 * @param title
	 */
 	public boolean isexist(String title);
	
	/**
	 * Get All AssignTaskDTO
	 * 
	 * @return List<AssignTaskDTO>
	 */
	public List<AssignTaskDTO> getAllAssignTaskDTO();
	
	/**
	 * Get All AssignTaskDTO
	 * 
	 * @return List<AssignTaskDTO>
	 */
	public List<AssignTaskDTO> getAllAssignTaskDTOByUsername(String username);

	/**
	 * Get AssignTaskDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	public AssignTaskDTO findAssignTaskDTOId(Integer id);

	/**
	 * Get AssignTaskDTO by title
	 * 
	 * @param title
	 * @return AssignTaskDTO
	 */
	public AssignTaskDTO findAssignTaskDTOTitlte(String title);
	
	/**
	 * Delete AssignTaskDTO by id
	 * @param id
	 */
	public boolean deleteAssignTaskDTOBy(Integer id);

	/**
	 * Delete AssignTaskDTO by title
	 * @param title
	 */
	public boolean deleteAssignTaskDTOByTitle(String title);


	/**
	 * Update AssignTaskDTO information, enable/disable etc..
	 * 
	 * @param boolean
	 */
	 public boolean updateAssignTaskDTO(AssignTaskDTO assignTaskDTO);
	 
	 /**
		 *Get total count of assignTaskDTO 
		 * 
		 * @param total
		 */
	public int getAssignTaskCount();
	
}
