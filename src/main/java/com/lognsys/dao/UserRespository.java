package com.lognsys.dao;

import java.util.List;
import com.lognsys.dao.dto.UsersDTO;

public interface UserRespository {

	/**
	 * Add user into database
	 * 
	 * @param users
	 */
	public int addUser(UsersDTO users);
	

//	public String getUsernamebyRealname(String realname);
	
	/**
	 * Check if User exists by username
	 * @param username
	 * @return boolean
	 */
	public boolean isExists(String username);

	/**
	 * Get All users
	 * 
	 * @return List<UsersDTO>
	 */
	public List<UsersDTO> getAllUsers();
	
	
	/**
	 * Delete user by id
	 * @param id
	 */
	public boolean deleteUserBy(Integer id);
	
	/**
	 * Delete user by email|D
	 * @param id
	 */
	public boolean deleteUserBy(String emailID);
	
	/**
	 * Get User by Id
	 * 
	 * @param id
	 * @return
	 */
	public UsersDTO findUserById(Integer id);
	
	/**
	 * Get User by emailId
	 * 
	 * @param emailid
	 * @return UsersDTO
	 */
	public UsersDTO findUserByUsername(String emailID);

	/**
	 * Update user information, enable/disable etc..
	 * 
	 * @param users
	 */
	 public boolean updateUser(UsersDTO user);
	
	 /**
		 *Get total count of user 
		 * 
		 * @param total
		 */
		public int getUserCount();
	  
}
