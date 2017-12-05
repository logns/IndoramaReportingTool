package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogBuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.model.DailyLog;

public class DailyLogAndBuByIdRowMapper  implements RowMapper<DailyLogBuDTO> {
	DailyLogBuDTO dailyLogBuDTO = new DailyLogBuDTO();

	@Override
	public DailyLogBuDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		dailyLogBuDTO.setId(rs.getInt("id"));
		BuDTO buDTO =new BuDTO();
		buDTO.setId(rs.getInt("bu_id"));	
		
		dailyLogBuDTO.setBuDTO(buDTO);
		
		DailyLog dailyLog=new DailyLog();
		dailyLog.setId(rs.getInt("dailylog_id"));
		
		dailyLogBuDTO.setDailylogDTO(dailyLog);
		
		return dailyLogBuDTO;
	}
}
