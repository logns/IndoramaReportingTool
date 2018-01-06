package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.UsersDTO;

public class AssignTaskRowMapper implements RowMapper<AssignTaskDTO> {
	AssignTaskDTO assignTaskDTO = new AssignTaskDTO();

	@Override
	public AssignTaskDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		assignTaskDTO.setId(rs.getInt("id"));
		assignTaskDTO.setTitle(rs.getString("title"));
		assignTaskDTO.setAssigned_to(rs.getString("assigned_to"));
		assignTaskDTO.setPriority(rs.getString("priority"));
		assignTaskDTO.setTarget_date(rs.getString("target_date"));
		assignTaskDTO.setDone_percentage(rs.getString("done_percentage"));
		assignTaskDTO.setCreated_by(rs.getString("created_by"));
		return assignTaskDTO;
	}
}