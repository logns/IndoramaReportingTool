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

import com.lognsys.dao.DepartmentRepository;
import com.lognsys.dao.dto.DepartmentsDTO;
import com.lognsys.dao.dto.UsersDepartmentsDTO;
import com.lognsys.util.Constants;

@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	// Injecting resource sql.properties.
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<DepartmentsDTO> getAllDepartments() {
		List<DepartmentsDTO> departmentlist = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DEPARTMENT_QUERIES.select_departments_all.name()),
				new BeanPropertyRowMapper<DepartmentsDTO>(DepartmentsDTO.class));

		return departmentlist;
	}

	@Override
	public String findDepartmentBy(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsersDepartmentsDTO> getUsersByDepartment(String department_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addDepartment(DepartmentsDTO departmentsDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateDepartmentOfUser(String userName, String department_name) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", userName).addValue("department_name",
				department_name);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DEPARTMENT_QUERIES.update_departments_byuser.name()),
				param)==1;
	}

	@Override
	public int getDepartmentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteDepartment(String department_name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isexist(String department_name) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
