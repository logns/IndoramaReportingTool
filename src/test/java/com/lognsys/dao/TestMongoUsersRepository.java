package com.lognsys.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.mongo.MongoUserRepository;
import com.lognsys.model.Users;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mongo-context.xml" })
public class TestMongoUsersRepository {

	@Autowired
	private MongoUserRepository mongoUserRepo;

	public void setUp() {

	}

	// TODO transaction test
	@Test
	public void testCreateUser() {
		Users users = new Users();



		users.setRealname("Priyank Doshi");

		users.setUsername("doshipriyank");

		users.setPhone("9867544359");

		users.setState("Maharashtra");

		users.setCity("Mumbai");


		users.setZipcode("400067");

		users.setAddress("Kandivali West");

		users.setNotification(true);

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);

		users.setBirthdate(currentTime);

		mongoUserRepo.addUser(ObjectMapper.mapToUsersDTO(users));

		Users u2 = new Users();
		u2.setId(1);
		u2.setRealname("Jelly Vora");
		u2.setNotification(false);
		u2.setUsername("jvora");

		mongoUserRepo.addUser(ObjectMapper.mapToUsersDTO(u2));

	}

}
