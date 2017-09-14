package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DepartmentsDTO;
import com.lognsys.dao.dto.UsersDepartmentsDTO;

public interface DepartmentRepository {

	// NOTE: This applies to department
	public List<DepartmentsDTO> getAllDepartments();

	// NOTE: This applies to department
	public String findDepartmentBy(int user_id);

	public List<UsersDepartmentsDTO> getUsersByDepartment(String department_name);

	// NOTE: this applies to department
	public int addDepartment(DepartmentsDTO departmentsDTO);

	
	// NOTE: This applies to only department
	public boolean updateDepartmentOfUser(String userName, String department_name);
	
	//Get total count of departments 
	public int getDepartmentCount();
  
	//This is department 
 	public boolean deleteDepartment(String department_name);
	
 	//Make sure department name is not repeated
	public boolean isexist(String department_name);

}
