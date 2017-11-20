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
import com.lognsys.dao.jdbc.JdbcBuRepository;
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
		// Check if User Exists
		/*		if (isexist(assignTaskDTO.getTitle())){
					throw new IllegalArgumentException("AssignTask already exists title - " + (assignTaskDTO.getTitle()));
	
				}*/
				
		System.out.println("Rest addAssignTask assignTaskDTO getTitle  " + assignTaskDTO.getTitle());
		System.out.println("Rest addAssignTask (jdbcAssignTaskRepository)  " + (jdbcAssignTaskRepository)+"\n");

/*		if(!(isexist(assignTaskDTO.getTitle()))
				&& assignTaskDTO.getTitle()!=null && assignTaskDTO.getTitle().length()>0){
			*/
			int assign_task_id = jdbcAssignTaskRepository.addAssignTask(assignTaskDTO);
			assignTaskDTO.setId(assign_task_id);
			
			System.out.println("Rest addAssignTask assign_task_id "+assign_task_id);			
			System.out.println("Rest addAssignTask dailyLogDTO tostring  " + dailyLogDTO.toString());

			
			int dailylog_id= jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
			System.out.println("Rest addAssignTask dailylog_id "+dailylog_id);

			int assignDailyLog_id=jdbcAssignTaskRepository.addAssignTask_DailyLog(assign_task_id, dailylog_id);
			System.out.println("Rest addAssignTask assignDailyLog_id "+assignDailyLog_id);			
			
			System.out.println("Rest addAssignTask dailyLogDTO.getBu() "+dailyLogDTO.getBu());		
			
			if(dailylog_id>0 && dailyLogDTO.getBu()!=null &&  dailyLogDTO.getBu().length()>0){
				int bu_id=jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
				if(bu_id>0 && bu_id!=0){
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
		listOfAssigntask =jdbcAssignTaskRepository.getAllAssignTaskDTO();
		
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
