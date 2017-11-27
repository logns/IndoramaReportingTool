package com.lognsys.service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.exolab.castor.types.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.AssignTaskDailylogDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.model.AssignTask;
import com.lognsys.model.AssignTaskTable;
import com.lognsys.model.DailyLog;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.AssignTaskService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("assigntaskService")
public class AssignTaskService {

	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;

	@Autowired
	private JdbcBuRepository jdbcBuRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	DailyLogDTO dailyLogDTO;
	AssignTaskDTO assignTaskDTO;
	List<AssignTaskDTO> listOfAssigntask;
	Hashtable< String, String> hashUpdatedby= new Hashtable<String, String>();

	/**
	 * Add assignTaskDTO,dailyLogDTO to database..
	 *
	 * DAILYLOGS CAN HAVE ONLY ONE ASSIGNTASK 
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addAssignTask(AssignTaskDTO assignTaskDTO, DailyLogDTO dailyLogDTO) throws IOException {
	
//		Adding assigntask in db
		int assign_task_id = jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
		assignTaskDTO.setId(assign_task_id);

//		before adding dailylog set the assigntask id
		dailyLogDTO.setAssign_task_id(assign_task_id);

//		adding dailylog
		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
	
//		adding both the tables id in assign_dailylog tables 
		int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assign_task_id, dailylog_id);
		
//		adding data to dailylog_bu
		if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
			int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
			
			if (bu_id > 0 && bu_id != 0) {
				jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
			}
		}
		try {
			hashUpdatedby.put(new Date().toString(),"Updated by"+ assignTaskDTO.getAssigned_to());
			setHashUpdatedby(hashUpdatedby);
//			reading assigntask
			readAssignTask();
		} catch (IOException io) {
			System.out.println("Rest addAssignTask  readAssignTask - " + io.getMessage());
		}
		return assign_task_id;
	}
	
public Hashtable<String, String> getHashUpdatedby() {
		return hashUpdatedby;
	}

	public void setHashUpdatedby(Hashtable<String, String> hashUpdatedby) {
		this.hashUpdatedby = hashUpdatedby;
	}

	//	checking isexist
	public boolean isexist(String title) {
		return jdbcAssignTaskRepository.isexist(title);
	}

	public void readAssignTask() throws IOException {
//		getting list of assigntask 
		listOfAssigntask = jdbcAssignTaskRepository.getAllAssignTaskDTO();

		List<AssignTaskTable> assignTaskTables = null;
		
		// adding the list of assigntask into  assigntasktable to  show in UI
		if (listOfAssigntask != null && listOfAssigntask.size() > 0) {
			assignTaskTables = ObjectMapper.mapToAssignTaskTable(listOfAssigntask);
		}

		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.assigntask_filename.name()));
		String list = CommonUtilities.convertToJSON(assignTaskTables);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			System.out.println("IOEXCEPTION --- e" + e);
			e.printStackTrace();
		}
	}
	
//		REMOVING ASSIGNTASK DATA FROM DATABASE	
	public int removeAssignTask(int[] ids) throws IOException {
		
		for (int id : ids) {
			try {

				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOBy(id);

				if (!isDelete) {
					return 0;
				} else {
					readAssignTask();
				}
			} catch (DataAccessException | IOException dae) {

				// LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}

		}
		return 1;
	}

	/**
	 * Delete ASSIGNTASK from database
	 * 
	 * @param String
	 *            titles[]
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */
	public int deleteTaskTitle(String[] titles) throws IOException {
		for (String title : titles) {
			try {
				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOByTitle(title);

				if (!isDelete) {
					return 0;
				} else {
					readAssignTask();
				}
			} catch (DataAccessException dae) {
				throw new IllegalStateException("Error : Failed to delete task!");
			}
		}
		return 1;
	}

//	get ASSIGNTASKDTO BY ID
	public AssignTaskDTO getAssigntaskDTOById(int id) {
		AssignTaskDTO assignTaskDTO = jdbcAssignTaskRepository.findAssignTaskDTOId(id);
		return assignTaskDTO;
	}

	/**
	 * UPDATE ASSIGNTASK
	 * 
	 * @param assignTaskDailylogDTO
	 */
	@Transactional
	public boolean updateAssigntask(AssignTaskDailylogDTO assignTaskDailylogDTO) {
		boolean isUpdated = false;
		try {
			
			AssignTaskDTO assignTaskDTO=assignTaskDailylogDTO.getAssignTaskDTO();
			dailyLogDTO=assignTaskDailylogDTO.getDailylogDTO();
			
			AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(assignTaskDTO.getTitle());
			assignTaskDTO.setId(dto.getId());
			
//			UPDATING ASSIGNTASK
			isUpdated = jdbcAssignTaskRepository.updateAssignTaskDTO(assignTaskDTO);
			System.out.println("updateAssigntask isUpdated == "+isUpdated);
			
			
			dailyLogDTO.setAssign_task_id(assignTaskDTO.getId());
			dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage());
			dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date());
			
//			ADDINGNEW ASSIGNTASK
			int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
			System.out.println("updateAssigntask addDailylog dailylog_id " + dailylog_id);

			int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assignTaskDTO.getId(), dailylog_id);
			System.out.println("updateAssigntask addDailylog assignDailyLog_id " + assignDailyLog_id);

//			adding data to dailylog_bu
			if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
				int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
				if (bu_id > 0 && bu_id != 0) {
					jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
					System.out.println("updateAssigntask addDailylog addDailyLogAndBu ");

				}
			}

			readAssignTask();

		} catch (DataAccessException dae) {
		System.out.println("\n \n updateAssigntask DataAccessException == "+dae.toString()+"\n \n");

			throw new IllegalStateException("Failed  update Task status - " + isUpdated);
		} catch (IOException e) {
			System.out.println("\n \n updateAssigntask IOException == "+e.toString()+"\n \n");
			throw new IllegalStateException(e);
		}
		return isUpdated;
	}

	public AssignTaskDTO getAssigntDTObyTitle(String title){
		AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(title);
		return dto;
	}
}
