package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.PasswordChangeRequestsDTO;

public class PCRRowMapper implements RowMapper<PasswordChangeRequestsDTO> {
	PasswordChangeRequestsDTO assignTaskDTO = new PasswordChangeRequestsDTO();

	@Override
	public PasswordChangeRequestsDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		assignTaskDTO.setHash_id(rs.getString("hash_id"));
		assignTaskDTO.setTime(rs.getString("time"));
		assignTaskDTO.setUsers_id(rs.getInt("users_id"));
		assignTaskDTO.setNo_of_attempts(rs.getInt("no_of_attempts"));
		return assignTaskDTO;
	}

}
