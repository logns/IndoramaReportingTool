package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DailyLogBuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.DailyLog;

public interface DailyLogRespository {

	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	public int addDailyLog(DailyLogDTO dailyLogDTO);

	/**
	 * Get All dailyLogDTO
	 * 
	 * @return List<DailyLogDTO>
	 */
	public List<DailyLogDTO> getAllDailyLogDTO();

	/**
	 * Get All dailyLogDTO by assign_task_title
	 * 
	 * @return List<DailyLogDTO>
	 */
	public List<DailyLogDTO> getDailyLogDTOByTitle(String assign_task_title);

	/**
	 * Get DailyLogDTO by Id and description
	 * 
	 * @param id
	 * @return
	 */
	public DailyLogDTO findDailyLogDTOByIdAndDescription(String description,Integer id);
	public DailyLogDTO findDailyLogDTOById(Integer id);
	public DailyLogBuDTO findDailyLogDTOBuByDailyLogId(Integer dailylog_id);
	
	/**
	 * Delete DailyLogDTO by id
	 * @param id
	 */
	public boolean deleteDailyLogDTOBy(Integer id);
	

	/**
	 * Delete DailyLogDTO by assign_task_id
	 * @param assign_task_id
	 */
	public boolean deleteDailyLog(Integer assign_task_id);

	/**
	 * Update DailyLogDTO information etc..
	 * 
	 * @param boolean
	 */
	 public boolean updateDailyLogDTO(DailyLogDTO dailyLogDTO);
	 
	 /**
		 *Get total count of dailylog 
		 * 
		 * @param total
		 */
	public int getDailyLogCount(String title);
	  
	
}
