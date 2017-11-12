package com.lognsys.service;

import java.io.IOException;
import java.util.List;

import com.lognsys.dao.dto.DailyLogDTO;

public interface DailyLogServices {
	
	public List<DailyLogDTO> fetchDailyLog(String assign_task_title) throws IOException;
}
