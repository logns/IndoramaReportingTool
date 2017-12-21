package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.PasswordChangeRequestsDTO;
import com.lognsys.dao.dto.UsersDTO;

public interface PasswordChangeRequestsRepository {

	public void addPCR(PasswordChangeRequestsDTO changeRequestsDTO);
	
	public List<PasswordChangeRequestsDTO> getAllPCR();
	
	public PasswordChangeRequestsDTO getPCRByUserId(int users_id);

	public PasswordChangeRequestsDTO findPCRByHash_id(String hash_id);
	
	public boolean isExists(int users_id);
	
	public boolean updatePasswordChangeRequests(PasswordChangeRequestsDTO passwordChangeRequestsDTO);
		
	
}
