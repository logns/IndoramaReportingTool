package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.UsersBuDTO;


public interface BuRepository {


	// NOTE: This applies to bu
	public List<BuDTO> getAllBu();

	// NOTE: This applies to bu
	public String findBuBy(int user_id);

	public List<UsersBuDTO> getUsersByBu(String bu_name);

	// NOTE: this applies to bu
	public int addbu(BuDTO budto);

	
	// NOTE: This applies to only bu
	public boolean updateBuOfUser(String userName, String bu_name);
	
	//Get total count of bus 
	public int getBuCount();
  
	//This is bu 
 	public boolean deleteBu(String bu_name);
	
 	//Make sure bu name is not repeated
	public boolean isexist(String bu_name);
	
	public List<UsersBuDTO> getAllUsersAndBu(); 
}
