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
import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.rowmapper.AssignTaskRowMapper;
import com.lognsys.dao.jdbc.rowmapper.UserByUserIDRowMapper;
import com.lognsys.util.Constants;

@Repository("assigntaskRepository")
public class JdbcAssignTaskRepository  implements AssignTaskRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	
	
	@Override
	public int addAssignTask(AssignTaskDTO assignTaskDTO) {

		int assign_task_id = 0;

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(assignTaskDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.insert_assign_task.name()), params,
				keyHolder);
		assign_task_id = keyHolder.getKey().intValue();

		return assign_task_id;

	}

	@Override
	public boolean isexist(String title) {
		System.out.println("isExists title - "+title);
		SqlParameterSource param = new MapSqlParameterSource("title", title);
			Integer val;
			val = namedParamJdbcTemplate.queryForObject(
					sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.select_isexist_assigntask_title.name()),
					param, Integer.class);
			System.out.println("isExists val - " + val);
			if (val != null && val > 0)
				return true;
			else
				return false;
		
	}

	@Override
	public List<AssignTaskDTO> getAllAssignTaskDTO() {
		List<AssignTaskDTO> lists = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.select_all_assigntask.name()),
				new BeanPropertyRowMapper<AssignTaskDTO>(AssignTaskDTO.class));
		return lists;
	}

	@Override
	public AssignTaskDTO findAssignTaskDTOId(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));

		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.select_assigntask_by_id.name()), parameter,
				new AssignTaskRowMapper());
	}

	@Override
	public boolean deleteAssignTaskDTOBy(Integer id) {
		SqlParameterSource parameter = new MapSqlParameterSource("id", Integer.valueOf(id));
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.delete_assign_task_by_id.name()),
				parameter) == 1;

	}

	@Override
	public boolean updateAssignTaskDTO(AssignTaskDTO assignTaskDTO) {

		boolean isUpdate = false;
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(assignTaskDTO);
		isUpdate = namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.update_assign_task.name()),
				params) == 1;
	
		return isUpdate;
	}

	@Override
	public int getAssignTaskCount() {
		List<AssignTaskDTO> lists = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.select_all_assigntask.name()),
				new BeanPropertyRowMapper<AssignTaskDTO>(AssignTaskDTO.class));
		 
		int count = lists.size();
		if(count>0)
			return count;
		else
			return 0;
	}

	@Override
	public AssignTaskDTO findAssignTaskDTOTitlte(String title) {
		SqlParameterSource parameter = new MapSqlParameterSource("title", title);

		AssignTaskDTO assignTaskDTO = namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.select_assigntask_by_title.name()), parameter,
				new AssignTaskRowMapper());

		return assignTaskDTO;

	}

	@Override
	public int addAssignTask_DailyLog(int assign_task_id, int dailylog_id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("assign_task_id",assign_task_id).addValue("dailylog_id", dailylog_id);
		
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ASSIGN_TASK_DAILYLOG_QUERIES.insert_assign_task_dailylog.name()), param,
				keyHolder);
		int assign_task_dailylog_id = keyHolder.getKey().intValue();
		
		return assign_task_dailylog_id;
	}

	@Override
	public boolean deleteAssignTaskDTOByTitle(String title) {
		SqlParameterSource parameter = new MapSqlParameterSource("title",title);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.ASSIGN_TASK_QUERIES.delete_assign_task_by_title.name()),
				parameter) == 1;
		}
}
