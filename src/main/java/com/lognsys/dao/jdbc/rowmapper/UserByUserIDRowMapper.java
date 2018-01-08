package com.lognsys.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lognsys.dao.dto.UsersDTO;

public class UserByUserIDRowMapper implements RowMapper<UsersDTO> {
	UsersDTO useritem = new UsersDTO();

	@Override
	public UsersDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		useritem.setId(rs.getInt("id"));
		useritem.setUsername(rs.getString("username"));
		useritem.setRealname(rs.getString("realname"));
		useritem.setEnabled(rs.getBoolean("enabled"));
		useritem.setAddress(rs.getString("address"));
		useritem.setPhone(rs.getString("phone"));
		useritem.setCity(rs.getString("city"));
		useritem.setState(rs.getString("state"));
		useritem.setZipcode(rs.getString("zipcode"));
		useritem.setPassword(rs.getString("password"));
		useritem.setBirthdate(rs.getString("birthdate"));
		return useritem;
	}
}
