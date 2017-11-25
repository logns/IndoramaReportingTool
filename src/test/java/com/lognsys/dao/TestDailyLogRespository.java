package com.lognsys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.model.DailyLog;
import com.lognsys.util.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestDailyLogRespository {


		@Autowired
		private JdbcDailyLogRepository jdbcDailyLogRepository;

	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	@Test
	public void addDailyLog(){

		DailyLog dldto = new DailyLog();
		dldto.setAssign_task_id(40);
		dldto.setAssign_task_title("Testing");
		dldto.setTarget_date("2018-01-01");
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

	/**
	 * Get All dailyLogDTO
	 * 
	 * @return List<DailyLogDTO>
	 */

	@Test
	public void getAllDailyLogDTO(){
		List<DailyLogDTO> dailyLogDTOs=jdbcDailyLogRepository.getAllDailyLogDTO();
		Assert.notNull(dailyLogDTOs, "Check list of getAllDailyLogDTO NOT NULL");

		Assert.notEmpty(dailyLogDTOs, "Collection not empty..list of getAllDailyLogDTO  object");

	}

		 
	 /**
		 *Get total count of dailylog 
		 * 
		 * @param total
		 */
	@Test
	public void getDailyLogCount(){
		int count= jdbcDailyLogRepository.getDailyLogCount();
		Assert.notNull(count, "Check list of count NOT NULL");
	}	
}