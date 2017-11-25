package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

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

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.model.AssignTask;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.UsersTable;
import com.lognsys.service.AssignTaskService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml"})
public class TestAssignTaskService {
	@Autowired
	private AssignTaskService assignTaskService;


	public void setUp() {

	}
	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	
	DailyLogDTO dailyLogDTO;
	AssignTaskDTO assignTaskDTO;
	List<AssignTaskDTO> listOfAssigntask;
	/**
	 * Add dailylog to database.. Check if user already exists in db
	 * 
	 * TODO : Add rollbackFor is users exists TODO : Add exception for users and
	 * roles and groups which has unique constraints
	 * 
	 * @return
	 * @throws IOException
	 */
	@Test
	public void addAssignTask() throws IOException {
		AssignTask at = new AssignTask();
		at.setTitle("AlphaBeta");
		at.setAssigned_to("Punish");
		at.setPriority("Low");
		at.setTarget_date("2018-09-04");
		at.setDone_percentage("0%");
		assignTaskDTO=ObjectMapper.mapToAssignTaskDTO(at);
		
		jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
		Assert.notNull(assignTaskDTO, "Check assignTaskDTO NOT NULL");
		
	}
	@Test
	public void addDailyLog(){

		DailyLog dldto = new DailyLog();
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

		DailyLogDTO dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dldto);

		int id_dailylog=jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		Assert.notNull(dailyLogDTO, "Check list of dailyLogDTO NOT NULL");
	}
	
//	reading assigntask
	@Test
	public void readAssignTask() throws IOException {
	assignTaskService.readAssignTask();
	}
	
//	removing assigntask by id
	@Test
	public void removeAssignTask() throws IOException {
	
		int[] ids={1,2};
		assignTaskService.removeAssignTask(ids);
	
	}

}
