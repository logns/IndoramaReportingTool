package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.AssignTaskRepository;
import com.lognsys.dao.PasswordChangeRequestsRepository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.PasswordChangeRequestsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.AssignTaskRowMapper;
import com.lognsys.dao.jdbc.rowmapper.PCRRowMapper;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("passwordchangerequestsRepository")
public class JdbcPasswordChangeRequestsRepository implements PasswordChangeRequestsRepository {


	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	
	@Override
	public void addPCR(PasswordChangeRequestsDTO changeRequestsDTO) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(changeRequestsDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.insert_passwordchangerequests.name()), params,
				keyHolder);


	}

	@Override
	public List<PasswordChangeRequestsDTO> getAllPCR() {
		List<PasswordChangeRequestsDTO> lists = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.select_all_pcr.name()),
				new BeanPropertyRowMapper<PasswordChangeRequestsDTO>(PasswordChangeRequestsDTO.class));
		return lists;
	}

	@Override
	public PasswordChangeRequestsDTO getPCRByUserId(int users_id) {
		SqlParameterSource parameter = new MapSqlParameterSource("users_id", users_id);

		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.selecr_pcr_users_id.name()), parameter,
				new PCRRowMapper());
		}

	@Override
	public boolean isExists(int users_id) {
		SqlParameterSource param = new MapSqlParameterSource("users_id", users_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.select_pcr_exists.name()), param, Integer.class) > 0;
	}

	@Override
	public boolean updatePasswordChangeRequests(PasswordChangeRequestsDTO passwordChangeRequestsDTO) {
		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(passwordChangeRequestsDTO);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.update_pcr.name()),
				params) == 1;
	
		return isUpdate;
	}

	@Override
	public PasswordChangeRequestsDTO findPCRByHash_id(String hash_id) {
		SqlParameterSource parameter = new MapSqlParameterSource("hash_id", hash_id);

		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.PASSWORDCHANGEREQUESTS_QUERIES.selecr_pcr_users_id.name()), parameter,
				new PCRRowMapper());
	}

}
