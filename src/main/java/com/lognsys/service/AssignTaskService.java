package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
	public int addAssignTask(AssignTaskDTO assignTaskDTO, DailyLogDTO dailyLogDTO) throws IOException {
		System.out.println("Rest addAssignTask assignTaskDTO getTitle  " + assignTaskDTO.getTitle());
		System.out.println("Rest addAssignTask (jdbcAssignTaskRepository)  " + (jdbcAssignTaskRepository) + "\n");
	
		int assign_task_id = jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
		assignTaskDTO.setId(assign_task_id);

		dailyLogDTO.setAssign_task_id(assign_task_id);

		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		System.out.println("Rest addAssignTask dailylog_id " + dailylog_id);

		int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assign_task_id, dailylog_id);
		System.out.println("Rest addAssignTask assignDailyLog_id " + assignDailyLog_id);

		System.out.println("Rest addAssignTask dailyLogDTO.getBu() " + dailyLogDTO.getBu());

		if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
			int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
			if (bu_id > 0 && bu_id != 0) {
				jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
				System.out.println("Rest addAssignTask addDailyLogAndBu ");

			}
		}
		try {
			readAssignTask();
		} catch (IOException io) {
			System.out.println("Rest addAssignTask  readAssignTask - " + io.getMessage());
		}
		return assign_task_id;

	}

	public boolean isexist(String title) {
		return jdbcAssignTaskRepository.isexist(title);
	}

	public void readAssignTask() throws IOException {
		listOfAssigntask = jdbcAssignTaskRepository.getAllAssignTaskDTO();

		List<AssignTaskTable> assignTaskTables = null;
		System.out.println("Rest readAssignTask listOfAssigntask.size() " + listOfAssigntask.size());

		if (listOfAssigntask != null && listOfAssigntask.size() > 0) {
			assignTaskTables = ObjectMapper.mapToAssignTaskTable(listOfAssigntask);
		}
		System.out.println("Rest readAssignTask assignTaskTables.size() " + assignTaskTables.size());

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

	public int removeAssignTask(int[] ids) throws IOException {
		// LOG.info("#deleteUser - " + "Deleting total number of users from
		// database - " + ids.length);

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
	 * Delete users from database
	 * 
	 * @param
	 * 
	 * @return
	 *
	 */
	public boolean deleteTaskId(Integer[] ids) {
		// LOG.info("#deleteUser - " + "Deleting total number of tasks from
		// database - " + ids.length);

		for (int id : ids) {
			try {

				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOBy(id);

				if (!isDelete) {
					return false;
				} else {
					readAssignTask();
				}
			} catch (DataAccessException | IOException dae) {

				// LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete task!");
			}

		}
		return true;
	}

	/**
	 * Delete users from database
	 * 
	 * @param String
	 *            emailID
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */
	public void deleteTaskTitle(String[] titles) throws IOException {
		// LOG.info("#deleteUser - " + "Deleting total number of titles from
		// database - " + titles.length);
		System.out.println("\n \n deleteTaskTitle titles ===== " + titles.length);
		for (String title : titles) {
			try {
				System.out.println("\n \n deleteTaskTitle title ===== " + title);

				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOByTitle(title);
				System.out.println("\n \n deleteTaskTitle isDelete ===== " + isDelete);

				if (!isDelete) {
					// LOG.info("#deleteUser - " + "failed to delete user with
					// ID - " + task);
				} else {
					readAssignTask();
				}
			} catch (DataAccessException dae) {

				// LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete task!");
			}
		}
	}

	public AssignTaskDTO getAssigntaskDTOById(int id) {
		AssignTaskDTO assignTaskDTO = jdbcAssignTaskRepository.findAssignTaskDTOId(id);
		return assignTaskDTO;
	}

	/**
	 * 
	 * 
	 * @param user
	 */
	@Transactional
	public boolean updateAssigntask(AssignTaskDailylogDTO assignTaskDailylogDTO) {
		boolean isUpdated = false;
		try {
			AssignTaskDTO assignTaskDTO=assignTaskDailylogDTO.getAssignTaskDTO();
			dailyLogDTO=assignTaskDailylogDTO.getDailylogDTO();
			System.out.println("\n \n updateAssigntask assignTaskDTO == "+assignTaskDTO.toString()+"\n \n");

			System.out.println("updateAssigntask assignTaskDTO == "+assignTaskDTO.toString());
			AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(assignTaskDTO.getTitle());
			System.out.println("\n \n updateAssigntask dto == "+dto.toString()+"\n \n");
			assignTaskDTO.setId(dto.getId());
		
			isUpdated = jdbcAssignTaskRepository.updateAssignTaskDTO(assignTaskDTO);
			System.out.println("updateAssigntask isUpdated == "+isUpdated);
			
			dailyLogDTO.setAssign_task_id(assignTaskDTO.getId());
			dailyLogDTO.setDone_percentage(assignTaskDTO.getDone_percentage());
			dailyLogDTO.setTarget_date(assignTaskDTO.getTarget_date());
			
			System.out.println("\n \n updateAssigntask dailyLogDTO == "+dailyLogDTO.toString()+"\n \n");
			
			int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
			System.out.println("updateAssigntask addDailylog dailylog_id " + dailylog_id);

			int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(assignTaskDTO.getId(), dailylog_id);
			System.out.println("updateAssigntask addDailylog assignDailyLog_id " + assignDailyLog_id);

			System.out.println("updateAssigntask addDailylog dailyLogDTO.getBu() " + dailyLogDTO.getBu());

			if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
				int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
				if (bu_id > 0 && bu_id != 0) {
					jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
					System.out.println("updateAssigntask addDailylog addDailyLogAndBu ");

				}
			}

			readAssignTask();

		} catch (DataAccessException dae) {
//			LOG.error(dae.getMessage());
			throw new IllegalStateException("Failed  update Task status - " + isUpdated);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return isUpdated;

	}

}
