package com.lognsys.dao.jdbc;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.DailyLogRespository;
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.DailyLogBuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.resultset.DailyLogResultSetExtractor;
import com.lognsys.dao.jdbc.rowmapper.DailyLogAndBuByIdRowMapper;
import com.lognsys.dao.jdbc.rowmapper.DailyLogByDescriptionAndIDRowMapper;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("dailylogRepository")
public class JdbcDailyLogRepository implements DailyLogRespository {

//	private static final Logger LOG = Logger.getLogger(JdbcUserRepository.class);

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
	public List<DailyLogDTO> getAllDailyLogDTO() {
		List<DailyLogDTO> lists = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_dailylogs_all.name()),
				new DailyLogResultSetExtractor());
			
		return lists;
	}

	@Override
	public boolean deleteDailyLogDTOBy(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateDailyLogDTO(DailyLogDTO dailyLogDTO) {
		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(dailyLogDTO);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.update_dailylog.name()),
				params) == 1;
	
		return isUpdate;
	}
	/**
	 * add DailyLog_Bu
	 * @param dailylog_id,bu_id
	 * @param 
	 */
	public void addDailyLogAndBu(int dailylog_id, int bu_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("dailylog_id", dailylog_id).addValue("bu_id", bu_id);
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

	@Override
	public int getDailyLogCount(String title) {
		SqlParameterSource parameter = new MapSqlParameterSource("title", title);

			List<DailyLogDTO> lists=namedParamJdbcTemplate.query(
					sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_dailylog_by_title.name()), parameter,
					new DailyLogResultSetExtractor());

		int count = lists.size();
		if(count>0)
			return count;
		else
			return 0;
	}


	@Override
	public List<DailyLogDTO> getDailyLogDTOByTitle(String title) {
		System.out.println("\n getDailyLogDTOByTitle DailyLogDTO title.toString ------------------------ "
				+ title.toString());
	
		SqlParameterSource parameter = new MapSqlParameterSource("title", title);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_dailylog_by_title.name()), parameter,
				new DailyLogResultSetExtractor());

	}



	@Override
	public DailyLogDTO findDailyLogDTOByIdAndDescription(String description, Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("description", description).addValue("id", id);
		
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_description_and_id.name()), parameter,
				new DailyLogByDescriptionAndIDRowMapper());
	}



	@Override
	public boolean deleteDailyLog( Integer assign_task_id) {
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("assign_task_id", assign_task_id);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.delete_by_assign_task_id.name()),
				parameter) == 1;
	}



	@Override
	public DailyLogDTO findDailyLogDTOById(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
	
		DailyLogDTO dailyLogDTO = namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_assigntask_id.name()),parameter,
				new BeanPropertyRowMapper<DailyLogDTO>(DailyLogDTO.class));

		return dailyLogDTO;
	}



	@Override
	public DailyLogBuDTO findDailyLogDTOBuByDailyLogId(Integer dailylog_id) {
SqlParameterSource parameter = new MapSqlParameterSource().addValue("dailylog_id", dailylog_id);
		
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_dailylog_bu_by_id.name()), parameter,
				new DailyLogAndBuByIdRowMapper());
	}

	public boolean updateBuOfDailyLogBu(DailyLogBuDTO dailyLogBuDTO) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("bu_id",dailyLogBuDTO.getBuDTO().getId()).addValue("dailylog_id",
				dailyLogBuDTO.getDailylogDTO().getId()).addValue("id",dailyLogBuDTO.getId());
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.BU_QUERIES.update_daily_bu.name()),
				param)==1;
	}



	public String getDailyLogTitleByAssigntaskId(int assign_task_id) {
		
	SqlParameterSource parameter = new MapSqlParameterSource().addValue("assign_task_id", assign_task_id);
		
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.DAILYLOG_QUERIES.select_daily_assign_title_by_task_id.name()), parameter,
				String.class);
	
	}



	public void addDailylogUsers(String username, int dailylog_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", username).addValue("dailylog_id", dailylog_id);
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.DAILYLOG_USERS_QUERIES.insert_dailylog_users.name()),
				param);
		
	}

}
