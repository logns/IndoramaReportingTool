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

@Service("assigntaskService")
public class AssignTaskService {

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
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public void addAssignTask(AssignTask assignTask, DailyLog dailyLog) throws IOException {

		assignTaskDTO = ObjectMapper.mapToAssignTaskDTO(assignTask);
		System.out.println("Rest addAssignTask assignTaskDTO tostring  " + assignTaskDTO.toString());

		if(!(jdbcAssignTaskRepository.isexist(assignTaskDTO.getTitle()))){
			int assignTask_id = jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
			System.out.println("Rest addAssignTask assignTask_id "+assignTask_id);

			
			dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dailyLog);
			System.out.println("Rest addAssignTask dailyLogDTO tostring  " + dailyLogDTO.toString());

			int dailylog_id= jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
			System.out.println("Rest addAssignTask dailylog_id "+dailylog_id);

			int assignDailyLog_id=jdbcAssignTaskRepository.addAssignTask_DailyLog(assignTask_id, dailylog_id);
			System.out.println("Rest addAssignTask assignDailyLog_id "+assignDailyLog_id);			
			
		}	
		else{
			throw new IllegalArgumentException("Assigned Task already exists with title - " + assignTaskDTO.getTitle());
		}

	}

	public void readAssignTask() throws IOException {
		listOfAssigntask =jdbcAssignTaskRepository.getAllAssignTaskDTO();
		
		
	/*	System.out.println("Rest readAssignTask listOfAssigntask.size() "+listOfAssigntask.size());			
		
		if(listOfAssigntask!=null && listOfAssigntask.size()>0){
			return listOfAssigntask;
		}
		else
		return  null;	
//				throw new IllegalArgumentException("Assigned Task already exists with title - " + assignTaskDTO.getTitle());
	*/
		
		List<AssignTaskTable> assignTaskTables =null;
		System.out.println("Rest readAssignTask listOfAssigntask.size() "+listOfAssigntask.size());			
		
		if(listOfAssigntask!=null && listOfAssigntask.size()>0)
		{
			assignTaskTables = ObjectMapper.mapToAssignTaskTable(listOfAssigntask);
		}
		System.out.println("Rest readAssignTask assignTaskTables.size() "+assignTaskTables.size());			
		
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader.getResource(applicationProperties.getProperty(Constants.JSON_FILES.assigntask_filename.name()));
		String list = CommonUtilities.convertToJSON(assignTaskTables);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
		System.out.println("IOEXCEPTION --- e"+e);
			e.printStackTrace();
		}
	}

	public int removeAssignTask(int[] ids) throws IOException {
//		LOG.info("#deleteUser - " + "Deleting total number of users from database - " + ids.length);

		for (int id : ids) {
			try {

				boolean isDelete = jdbcAssignTaskRepository.deleteAssignTaskDTOBy(id);

				if (!isDelete) {
					return 0;
				} else {
					readAssignTask();
				}
			} catch (DataAccessException | IOException dae) {

//				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete user!");
			}
			
		}return 1;	
	}

}
