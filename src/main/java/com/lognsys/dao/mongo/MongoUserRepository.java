package com.lognsys.dao.mongo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.UsersDTO;

@Repository
public class MongoUserRepository implements UserRespository {

//	@Autowired
//	private MongoTemplate mongoTemplate;

	@Override
	public int addUser(UsersDTO users) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}


	

	@Override
	public List<UsersDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserBy(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsersDTO findUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserBy(String emailID) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean updateUser(UsersDTO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsersDTO findUserByUsername(String emailID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserDevice(String device, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UsersDTO> getUserRealNames() {
		// TODO Auto-generated method stub
		return null;
	}





}
