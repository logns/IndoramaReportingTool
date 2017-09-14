package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.DailyLog;

public interface DailyLogRespository {

	/**
	 * Add user into database
	 * 
	 * @param users
	 */
	public int addDailyLog(DailyLogDTO dailyLogDTO);
	
	
	/**
	 * Check if User exists by username
	 * @param username
	 * @return
	 */
	public boolean isExists(String username);

	/**
	 * Get All users
	 * 
	 * @return
	 */
	public List<DailyLogDTO> getAllDailyLogDTO();


	/**
	 * Delete user by id
	 * @param id
	 */
	public boolean deleteDailyLogDTOBy(Integer id);

	/**
	 * Get User by Id
	 * 
	 * @param id
	 * @return
	 */
	public DailyLogDTO findDailyLogDTOId(Integer id);
	
	/**
	 * Delete user by email|D
	 * @param id
	 */
	public boolean deleteDailyLogDTOBy(String username);


	/**
	 * Update user information, enable/disable etc..
	 * 
	 * @param users
	 */
	 public boolean updateDailyLogDTO(DailyLogDTO dailyLogDTO);
	 
	
}
