package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.RolesDTO;

public interface RoleRepository {

	public List<RolesDTO> getAllRoles();

	public String getRoleBy(int userId);
	
	

}
