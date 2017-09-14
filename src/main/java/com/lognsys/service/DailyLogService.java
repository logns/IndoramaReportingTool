package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.DepartmentsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.dao.jdbc.JdbcDepartmentRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("dailylogService")
public class DailyLogService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcBuRepository jdbcBuRepository;
	
	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	
	/**
	 * Add dailylog to database.. Check if user already exists in db
	 * 
	 * TODO : Add rollbackFor is users exists TODO : Add exception for users and
	 * roles and groups which has unique constraints
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addDailyLog(DailyLog dailyLog) throws IOException {
		
		// convert UserDTO -> User Object
		DailyLogDTO dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dailyLog);
		System.out.println("service dailyLogDTO.toString() - " + dailyLogDTO.toString());

				
		// adding user into db
		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		System.out.println("service  dailylog_id "+dailylog_id);

		// adding dailyLog into bu
		jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, dailyLog.getBu());
		
		// adding dailyLog into user
		jdbcDailyLogRepository.addDailyLogAndUser(dailylog_id, dailyLog.getRealname());
			
		return dailylog_id;
	}
}
