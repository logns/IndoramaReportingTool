package com.lognsys.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.DailylogTable;
import com.lognsys.service.DailyLogService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml"})
public class TestDailyLogService {

	@Autowired
	private DailyLogService dailyLogService;


	public void setUp() {

	}
	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;

	@Autowired
	private JdbcBuRepository jdbcBuRepository;
	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * Add dailylog to database.. Check if user already exists in db
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	@Test
	public void addDailyLog() throws IOException {
		DailyLog dldto=new DailyLog();
		dldto.setAssign_task_id(40);
		dldto.setTarget_date("2018-09-09");
		dldto.setShift("Morning");
		dldto.setMachine("M1");
		dldto.setDescription("Test Test Test");
		dldto.setTimefrom("11:30:00");
		dldto.setTimeto("23:40:00");
		dldto.setSpareparts("spare parts");
		dldto.setAttendby("attend by");
		dldto.setJobtype("Job");
		dldto.setRecordtype("Record");
		dldto.setStatus("Open");
		dldto.setDone_percentage("0%");

		dldto.setAssign_task_title("abc");
		
		// convert UserDTO -> User Object
		DailyLogDTO dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dldto);
		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		Assert.notNull(dailyLogDTO, "Check list of dailyLogDTO NOT NULL");
		
		int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(dailyLogDTO.getAssign_task_id(),
				dailylog_id);

		
		if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
			int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
			if (bu_id > 0 && bu_id != 0) {
				jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
			}
		}
	}

	@Test
	public void refresDailyListReport() {
		List<DailyLogDTO> lists = (jdbcDailyLogRepository.getAllDailyLogDTO());

		Assert.notNull(lists, "Check list of dailyLogDTO NOT NULL");

	}

	@Test
	public void getRealName() {

		List<UsersDTO> realnames;

		try {
			realnames = jdbcUserRepository.getUserRealNames();
			Assert.notNull(realnames, "Check list of dailyLogDTO NOT NULL");

		} catch (DataAccessException dae) {
			// LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to find username!");
		}
	}

	@Test
	public void getDailLogbyDescriptionAndId() {
		String description="sdsd";
		int id=40;
		DailyLogDTO dailyLogDTO = (jdbcDailyLogRepository.findDailyLogDTOByIdAndDescription(description, id));
		Assert.notNull(dailyLogDTO, "Check list of dailyLogDTO NOT NULL");

	}
	@Test
	public void deleteDailyLogs() {
		jdbcDailyLogRepository.deleteDailyLog(58);
	}

}
