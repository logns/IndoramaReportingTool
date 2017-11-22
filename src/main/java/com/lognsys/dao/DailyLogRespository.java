package com.lognsys.dao;

import java.util.List;

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
	 * Get All dailyLogDTO
	 * 
	 * @return List<DailyLogDTO>
	 */
	public List<DailyLogDTO> getDailyLogDTOByTitle(String assign_task_title);

	/**
	 * Get DailyLogDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	public DailyLogDTO findDailyLogDTOByIdAndDescription(String description,Integer id);
	
	/**
	 * Delete DailyLogDTO by id
	 * @param id
	 */
	public boolean deleteDailyLogDTOBy(Integer id);


	/**
	 * Update DailyLogDTO information, enable/disable etc..
	 * 
	 * @param boolean
	 */
	 public boolean updateDailyLogDTO(DailyLogDTO dailyLogDTO);
	 
	 /**
		 *Get total count of dailylog 
		 * 
		 * @param total
		 */
	public int getDailyLogCount();
	  
	
}
