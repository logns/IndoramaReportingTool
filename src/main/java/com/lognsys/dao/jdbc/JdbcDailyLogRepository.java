package com.lognsys.dao.jdbc;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.DailyLogRespository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("dailylogRepository")
public class JdbcDailyLogRepository implements DailyLogRespository {

	private static final Logger LOG = Logger.getLogger(JdbcUserRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public int addDailyLog(DailyLogDTO dailyLogDTO) {

		int dailylog_id = 0;

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(dailyLogDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.insert_dailylog.name()), params,
				keyHolder);

		dailylog_id = keyHolder.getKey().intValue();

		return dailylog_id;
	}

	@Override
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DailyLogDTO> getAllDailyLogDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDailyLogDTOBy(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DailyLogDTO findDailyLogDTOId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDailyLogDTOBy(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDailyLogDTO(DailyLogDTO dailyLogDTO) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 
	 * @param dailylog_id
	 * @param bu
	 */
	public void addDailyLogAndBu(int dailylog_id, String bu_name) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("dailylog_id", dailylog_id).addValue("bu_name", bu_name);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.insert_dailylog_bu.name()),
				param);
			
	}
	/**
	 * 
	 * @param dailylog_id
	 * @param realname
	 */
	public void addDailyLogAndUser(int dailylog_id, String realname) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("dailylog_id", dailylog_id).addValue("realname", realname);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.insert_dailylog_users.name()),
				param);
		
	}

	
}
