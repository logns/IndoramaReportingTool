package com.lognsys.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.model.AssignTask;
import com.lognsys.model.DailyLog;

public interface AssignTaskServices {
	
	public void	addAssignTask(AssignTask assignTask,DailyLog dailyLog) throws IOException;

	public List<AssignTaskDTO>	readAssignTask() throws IOException;

	public int	removeAssignTask(int[] id) throws IOException;
}
