package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.UsersBuDTO;


public interface BuRepository {

	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	public int addBu(BuDTO budto);


	/**
	 * Get All BuDTO
	 * 
	 * @return List<BuDTO>
	 */
	public List<BuDTO> getAllBu();

	/**
	 * Get DailyLogDTO by user_id
	 * 
	 * @param user_id
	 * @return
	 */
	public String findBuBy(int user_id);

	/**
	 * Get All UsersBuDTO
	 * 
	 * @return List<UsersBuDTO>
	 */
	public List<UsersBuDTO> getUsersByBu(String bu_name);

	/**
	 * Update Bu Of User etc..
	 * 
	 * @param boolean
	 */
	public boolean updateBuOfUser(String userName, String bu_name);
	
	/**
	 *Get total count of bus 
	 * 
	 * @param total
	 */
	public int getBuCount();
  
	//This is bu 
 	public boolean deleteBu(String bu_name);
	
 	//Make sure bu name is not repeated
 	/**
	 *check bu isExits 
	 * 
	 * @param boolean
	 */
 	public boolean isexist(String bu_name);
	
	public List<UsersBuDTO> getAllUsersAndBu(); 
}
