package com.lognsys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogBuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.model.AssignTask;
import com.lognsys.model.DailyLog;
import com.lognsys.util.ObjectMapper;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestAssignTaskRepository {


	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;


	@Autowired
	private JdbcBuRepository jdbcBuRepository;

	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	@Test
	public void addAssignTask(){
	
		AssignTask at = new AssignTask();
		at.setTitle("Testing_two");
		at.setAssigned_to("Punish");
		at.setPriority("Low");
		at.setTarget_date("2018-09-04");
		at.setDone_percentage("0%");
		
	AssignTaskDTO	assignTaskDTO = ObjectMapper.mapToAssignTaskDTO(at);
	int id_assign=jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
	Assert.notNull(assignTaskDTO, "Check list of assignTaskDTO NOT NULL");
	

	DailyLog dldto = new DailyLog();
//	dldto.setAssign_task_title(at.getTitle());
	dldto.setAssign_task_id(id_assign);
	dldto.setTarget_date(at.getTarget_date());
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
	dldto.setDone_percentage("10%");

//	DailyLogDTO dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dldto);
//
//	int id_dailylog=jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
//	Assert.notNull(dailyLogDTO, "Check list of dailyLogDTO NOT NULL");
//
//	dailyLogDTO.setId(id_dailylog);
	
//	BuDTO buDTO=new BuDTO();
//	buDTO.setBu_name("UT");
//	dailyLogDTO.setBu(buDTO.getBu_name());
//	DailyLogBuDTO dbDTO= new DailyLogBuDTO();
//	dbDTO.setDailylogDTO(dldto);
//	if (id_dailylog > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
//		int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
//		if (bu_id > 0 && bu_id != 0) {
//			jdbcDailyLogRepository.addDailyLogAndBu(id_dailylog, bu_id);
//			System.out.println("Rest addAssignTask addDailyLogAndBu ");
//			Assert.notNull(dailyLogDTO, "Check list of addDailyLogAndBu NOT NULL");
//
//		}
//	}
	
	}
	
	/**
	 * Add dailyLogDTO into database
	 * 
	 * @param dailyLogDTO
	 */
	@Test
	public void addAssignTask_DailyLog(){
		int assignTask_id;
		int dailylog_id;
		
		assignTask_id =40;
		dailylog_id=40;
		int assignDailyLog_id=jdbcAssignTaskRepository.addAssignTask_DailyLog(assignTask_id, dailylog_id);
		Assert.notNull(assignTask_id, "Check list of assignTask_id NOT NULL");
		Assert.notNull(dailylog_id, "Check list of dailylog_id NOT NULL");
		Assert.notNull(assignDailyLog_id, "Check list of assignDailyLog_id NOT NULL");

	}
	
	/**
	 * Get All dailyLogDTO
	 * 
	 * @return List<AssignTaskDTO>
	 */
	@Test
	public void getAllAssignTaskDTO(){
		List<AssignTaskDTO> assignTaskDTOs=jdbcAssignTaskRepository.getAllAssignTaskDTO();
		Assert.notNull(assignTaskDTOs, "Check list of getAllAssignTaskDTO NOT NULL");

		Assert.notEmpty(assignTaskDTOs, "Collection not empty..list of getAllAssignTaskDTO  object");
		
	}

	/**
	 * Get AssignTaskDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void findAssignTaskDTOId(){
		

		AssignTaskDTO assignTaskDTO= jdbcAssignTaskRepository.findAssignTaskDTOId(40);
		Assert.notNull(assignTaskDTO, "Check list of assignTaskDTO NOT NULL");
	}

	/**
	 * Get AssignTaskDTO by Id
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void findAssignTaskDTOTitlte(){
		

		AssignTaskDTO assignTaskDTO= jdbcAssignTaskRepository.findAssignTaskDTOTitlte("AssignTask one");
		Assert.notNull(assignTaskDTO, "Check list of assignTaskDTO NOT NULL");
	}
	
	/**
	 * Delete AssignTaskDTO by id
	 * @param id
	 */
	@Test
	public void deleteAssignTaskDTOBy(){
		boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOBy(40);
		Assert.isTrue(isDelete, "Check list of deleteAssignTaskDTOBy ");

		
	}


	/**
	 * Update AssignTaskDTO information, enable/disable etc..
	 * 
	 * @param boolean
	 */
	@Test
	public void updateAssignTaskDTO(){
			AssignTaskDTO assignTaskDTO = new AssignTaskDTO();
			assignTaskDTO.setTitle("Testing_five");

		 boolean isDelete = jdbcAssignTaskRepository.updateAssignTaskDTO(assignTaskDTO);
			Assert.isTrue(isDelete, "Check list of deleteAssignTaskDTOBy ");

	 }
	 
	 /**
		 *Get total count of assignTaskDTO 
		 * 
		 * @param total
		 */
	@Test
	public void getAssignTaskCount(){
		int count= jdbcAssignTaskRepository.getAssignTaskCount();
		Assert.notNull(count, "Check list of count NOT NULL");

	}
	
}
