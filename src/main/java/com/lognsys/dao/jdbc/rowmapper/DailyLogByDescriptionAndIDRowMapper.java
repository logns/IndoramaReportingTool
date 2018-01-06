package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;

public class DailyLogByDescriptionAndIDRowMapper implements RowMapper<DailyLogDTO> {
	DailyLogDTO item = new DailyLogDTO();

	@Override
	public DailyLogDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		item.setId(rs.getInt("id"));
		item.setAssign_task_id(rs.getInt("assign_task_id"));
		item.setTarget_date(rs.getString("target_date"));
		item.setShift(rs.getString("shift"));
		item.setMachine(rs.getString("machine"));
		item.setDescription(rs.getString("description"));
		item.setTimefrom(rs.getString("timefrom"));
		item.setTimeto(rs.getString("timeto"));
		item.setSpare_parts(rs.getString("spare_parts"));
		item.setAttendby(rs.getString("attendby"));
		item.setJobtype(rs.getString("jobtype"));
		item.setRecordtype(rs.getString("recordtype"));
		item.setStatus(rs.getString("status"));
		item.setDone_percentage(rs.getString("done_percentage"));
		System.out.println("mapRow ======= rs.getString(bu_name)==============="+rs.getString("bu_name")+"\n\n\n");
		
		item.setBu(rs.getString("bu_name"));
		return item;
	}
}
