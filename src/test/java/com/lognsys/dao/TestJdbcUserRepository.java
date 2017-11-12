package com.lognsys.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Users;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcUserRepository {

	@Autowired
	private JdbcUserRepository userRepo;

	public void setUp() {

	}

	 @Test
	 public void testCreateUser() {
	 Users users = new Users();
	
	
	 users.setRealname("Priyank Doshi");
	
	 users.setUsername("doshipriyank11@gmail.com");
	
	 users.setPhone("9867544359");
	
	 users.setState("Maharashtra");
	
	 users.setCity("Mumbai");
	
	 users.setZipcode("400067");
	
	 users.setAddress("Kandivali West");
	
	 users.setNotification(true);
	//
	 java.util.Date dt = new java.util.Date();
	 java.text.SimpleDateFormat sdf = new
	 java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String currentTime = sdf.format(dt);
	 users.setBirthdate(currentTime);
//	   assertNull(users.getId());
	//
	 UsersDTO usersDTO = ObjectMapper.mapToUsersDTO(users);
	int id= userRepo.addUser(usersDTO);
	users.setId(id);
	 assertNotNull(users.getId());
     assertTrue(users.getId() > 0);
	 }
	 
	@Test
	public void testUpdateUser() {

		Users users = new Users();

		users.setFirstname("Priyank");
		

		users.setUsername("doshipriyank11@gmail.com");

		users.setPhone("9867544359");

		users.setState("Maharashtra");

		users.setCity("Mumbai");

		users.setZipcode("400067");

		users.setAddress("Kandivali West");

		userRepo.updateUser(ObjectMapper.mapToUsersDTO(users));
		
		//Assert.state(userRepo.updateUser(ObjectMapper.mapToUsersDTO(users), "Update User Test to true"));
	}
	
	@Test
	public void testGetUser(){
		
		String username = "doshipriyank@gmail.com";
		
		UsersDTO usersDTO = userRepo.findUserByUsername(username);
		Assert.notNull(usersDTO, "UserObject Null");
		
		
	}

}
