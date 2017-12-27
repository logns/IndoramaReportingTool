package com.lognsys.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.model.Users;
import com.lognsys.util.Constants;

@Repository("userRepository")
public class JdbcUserRepository implements UserRespository {

//	private static final Logger LOG = Logger.getLogger(JdbcUserRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	/**
	 * Add users object into database
	 * 
	 * @param users
	 * @return Returns auto-generated value from database
	 */
	@Override
	public int addUser(UsersDTO users) throws DataAccessException {

		int user_id = 0;

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.insert_users.name()), params,
				keyHolder);

		user_id = keyHolder.getKey().intValue();

		return user_id;
	}

	/**
	 * Returns boolean true if user exists
	 * 
	 * @param username
	 *            - String
	 * 
	 */
	@Override
	public boolean isExists(String username) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("username", username);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_exists.name()), param, Integer.class) > 0;
	}

	/**
	 * update users table in the database. 
	 *  
	 * @param UsersDTO 
	 */
	@Override
	public boolean updateUser(UsersDTO users) throws DataAccessException {

		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.update_users.name()),
				params) == 1;
	
		return isUpdate;
	}
	public boolean updateUserPasswordById(Users users) throws DataAccessException {

		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(users);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.update_users_password.name()),
				params) == 1;
	
		return isUpdate;
	}
	/**
	 * Returns Users object by id
	 * 
	 * @param id
	 *            - Integer
	 * 
	 */
	@Override
	public UsersDTO findUserById(Integer id) throws DataAccessException {

		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));

		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_id.name()), parameter,
				new UserByUserIDRowMapper());
	}

	/**
	 * Returns List<Users> from database
	 */
	@Override
	public List<UsersDTO> getAllUsers() throws DataAccessException {
		List<UsersDTO> listUsers = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users.name()),
				new BeanPropertyRowMapper<UsersDTO>(UsersDTO.class));

		return listUsers;
	}


	/**
	 * delete user by user_id
	 * 
	 * @param id
	 *            - integer
	 * 
	 */
	@Override
	public boolean deleteUserBy(Integer user_id) throws DataAccessException {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(user_id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users.name()),
				parameter) == 1;
	}

	/**
	 * delete user by emailID
	 * 
	 * @param  emailID
	 * 
	 */
	@Override
	public boolean deleteUserBy(String emailID) throws DataAccessException {
		SqlParameterSource parameter = new MapSqlParameterSource("emailID", emailID);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.USER_QUERIES.delete_users_email.name()), parameter) == 1;
	}

	/**
	 * Returns UsersDTO object or null
	 * 
	 * @param username
	 */
	@Override
	public UsersDTO findUserByUsername(String username) throws DataAccessException {
		System.out.println( "findUserByUsername username"+username);
		
		SqlParameterSource parameter = new MapSqlParameterSource("username", username);

		UsersDTO usersDTO = namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_users_username.name()), parameter,
				new UserByUserIDRowMapper());
		System.out.println( "findUserByUsername usersDTO"+usersDTO.toString());
		
		return usersDTO;

	}

	/**
	 * 
	 * 
	 * @param users_id
	 * @param role
	 */
	public void addUserAndRole(int users_id, String role) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource().addValue("users_id", users_id).addValue("role", role);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ROLES_QUERIES.insert_users_roles.name()),
				param);
		
	}

	/**
	 * 
	 * @param users_id
	 * @param department_name
	 */
	public void addUserAndDepartment(int users_id, String department_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource().addValue("users_id", users_id).addValue("department_name",
				department_name);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEPARTMENT_QUERIES.insert_user_departments.name()),
				param);

	}
	/**
	 * 
	 * @param users_id
	 * @param bu_name
	 */
	public void addUserAndBu(int users_id, String bu_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource().addValue("users_id", users_id).addValue("bu_name",
				bu_name);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BU_QUERIES.insert_user_bu.name()),
				param);

	}


	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<UsersDTO> getUserRealNames() {
		List<UsersDTO> listUsers = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.USER_QUERIES.select_realname.name()),
				new BeanPropertyRowMapper<UsersDTO>(UsersDTO.class));

		return listUsers;
		}

	public void getUsernamebyRealname(String realname) throws DataAccessException {
		System.out.println("\n getUsernamebyRealname realname "+realname);
			SqlParameterSource param = new MapSqlParameterSource("realname", realname.toString());

			List<String> listUsers = namedParamJdbcTemplate.query(
					sqlProperties.getProperty(Constants.USER_QUERIES.select_username.name()),param,
					new BeanPropertyRowMapper<String>(String.class));
	}

}
