package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.util.Constants;

@Repository
public class JdbcRolesRepository implements RoleRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<RolesDTO> getAllRoles() {
		// TODO Auto-generated method stub

		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.ROLES_QUERIES.select_roles_all.name()),
				new BeanPropertyRowMapper<RolesDTO>(RolesDTO.class));

	}

	/**
	 * Returns <String>role</String> from database based on user_id 
	 * 
	 * sql join queries from tables (users, users_roles, roles)
	 * 
	 * @param user_id
	 */
	@Override
	public String getRoleBy(int users_id) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource("users_id", users_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.ROLES_QUERIES.select_role_byuserid.name()), param,
				String.class);
	}
	
	
	/**
	 * Update user role
	 * 
	 * @param userName
	 * @param roleName
	 * @return
	 */
	public boolean updateRoleOfUser(String userName, String roleName) throws DataAccessException{
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", userName).addValue("role",
				roleName);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROLES_QUERIES.update_roles_byuser.name()),
				param)==1;

	}

}
