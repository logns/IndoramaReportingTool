package com.lognsys.dao.jdbc;

/**
 * Description :  Groups DTO Object is mapped with fields in MySQL table
 * 
 * Update: 
 * PJD - 18/07/17 : Fields is_subgroup & parent_id is added to fields
 * 
 */
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.BuRepository;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DepartmentsDTO;
import com.lognsys.dao.dto.UsersBuDTO;
import com.lognsys.dao.jdbc.resultset.UserBuResultSetExtractor;
import com.lognsys.util.Constants;

@Repository
public class JdbcBuRepository implements BuRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	// Injecting resource sql.properties.
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<BuDTO> getAllBu() {
		List<BuDTO> departmentlist = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.BU_QUERIES.select_bu_all.name()),
				new BeanPropertyRowMapper<BuDTO>(BuDTO.class));

		return departmentlist;
	}

	@Override
	public String findBuBy(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsersBuDTO> getUsersByBu(String bu_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addbu(BuDTO budto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateBuOfUser(String userName, String bu_name) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", userName).addValue("bu_name",
				bu_name);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BU_QUERIES.update_bu_byuser.name()),
				param)==1;
	}

	@Override
	public int getBuCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteBu(String bu_name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isexist(String bu_name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UsersBuDTO> getAllUsersAndBu() {
		List<UsersBuDTO> listOfUsersBuDTO = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.BU_QUERIES.select_usersbu_all.name()),
				new UserBuResultSetExtractor());

		//th
		if(listOfUsersBuDTO.isEmpty())
			throw new EmptyResultDataAccessException(0);
		
		return listOfUsersBuDTO;
	}

}
