package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;

public interface AssignTaskRepository {

	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	public int addAssignTask(AssignTaskDTO assignTaskDTO);
	
	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	public int addAssignTask_DailyLog(int assignTask_id,int dailylog_id);
	
	
	/**
	 *check bu isExits 
	 * 
	 * @param boolean
	 */
 	public boolean isexist(String title);
	
	/**
	 * Get All dailyLogDTO
	 * 
	 * @return List<AssignTaskDTO>
	 */
	public List<AssignTaskDTO> getAllAssignTaskDTO();

	/**
	 * Get AssignTaskDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	public AssignTaskDTO findAssignTaskDTOId(Integer id);

	/**
	 * Get AssignTaskDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	public AssignTaskDTO findAssignTaskDTOTitlte(String title);
	
	/**
	 * Delete AssignTaskDTO by id
	 * @param id
	 */
	public boolean deleteAssignTaskDTOBy(Integer id);


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
