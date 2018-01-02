package com.lognsys.dao.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersBuDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.util.Constants;

public class DailyLogResultSetExtractor  implements ResultSetExtractor<List<DailyLogDTO>> {

	@Override
	public List<DailyLogDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<DailyLogDTO> listOfUsersDTO = new ArrayList<>();
		while (rs.next()) {
		
			// Union of UsersDTO and Groups object
			DailyLogDTO u = new DailyLogDTO(rs.getInt(Constants.DAILYLOG_TABLE_FIELD_NAMES.id.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.last_edit.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.shift.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.machine.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.description.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.timefrom.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.timeto.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.spare_parts.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.attendby.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.jobtype.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.recordtype.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.status.name()),
					rs.getString(Constants.DAILYLOG_TABLE_FIELD_NAMES.done_percentage.name()));
			AssignTaskDTO a=new AssignTaskDTO();
			a.setTitle(rs.getString(Constants.ASSIGNTASK_TABLE_FIELD_NAMES.title.name()));
			u.setAssign_task_title(a.getTitle());
			BuDTO bu=new BuDTO();
			bu.setBu_name(rs.getString(Constants.BU_FIELDNAME.bu_name.name()));
			u.setBu(bu.getBu_name());
			listOfUsersDTO.add(u);
		}

		return listOfUsersDTO;
	}

}
