package com.lognsys.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.jdbc.JdbcRolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcRolesRepository {

	@Autowired
	private JdbcRolesRepository rolesRepo;

	public void setUp() {

	}

	@Test
	public void testGetAllRoles() {
		List<RolesDTO> ug = rolesRepo.getAllRoles();
		Assert.notNull(ug, "Check list of Users NOT NULL");
		Assert.notEmpty(ug, "Collection not empty..list of Users and Group object");
	}

	@Test
	public void testGetRoleBy() {
		int user_id = 15;
		String expected = "Critics";
		String actual = rolesRepo.getRoleBy(user_id);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateRoleForUser() {

		String username = "jdoshi@gmail.com";
		String roleName = "ADMIN";
		boolean isUpdate = rolesRepo.updateRoleOfUser(username, roleName);
		Assert.isTrue(isUpdate, "Updating Role - " + roleName + "for User - " + username);
	}

}
