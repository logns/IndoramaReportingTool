package com.lognsys.service;

import com.lognsys.model.PasswordChangeRequests;
import com.lognsys.model.Users;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.PasswordChangeRequestsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcPasswordChangeRequestsRepository;
import com.lognsys.dao.jdbc.JdbcRolesRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.exception.UserDataAccessException;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("passwordchangerequestsService")
public class PasswordChangeRequestsService {

	@Autowired
	MailService mailservice;
	
	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	@Autowired
	private JdbcPasswordChangeRequestsRepository jdbcPCRRepository;

	@Autowired
	private MessageSource msg;
	
	@Autowired
	MailService mailService;
	int count=0;
	Authentication  authentication;
	String randomString;
	String hashStringId;
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	
	public void forgotPassword(Users users) {
		String emailid=users.getUsername();
		String forgotPassword="Forgot Password Details";
		randomString=givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
		hashStringId=generateHashShakeyFromString(randomString);
		
		String message = msg.getMessage("forgotusername",
				new Object[] {emailid,"http://localhost:8080/resetpassword?id="+randomString}, null);
		processMail(users, forgotPassword, message);
		
		
	try {
		System.out.println( "forgotPassword emailid"+emailid);
		Users usersdetail = ObjectMapper.mapToUsers(jdbcUserRepository.findUserByUsername(emailid));
		
		Calendar cal = Calendar.getInstance();
	     cal.setTime(new Date());
	     System.out.println("sending mail --cal.getTime() "+cal.getTime());
			
	     if(hashStringId!=null && usersdetail!=null){
	    	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		     String currentdate =  sdf.format(cal.getTime()); 
		     System.out.println("sending mail after formate  currentdate "+currentdate);
				    
	    	 savePaswordChangeRequest(hashStringId,currentdate.toString(),usersdetail);
		}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("sending mail --EmptyResultDataAccessException "+e.toString());
			
			/*	throw new UserDataAccessException(
				applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_userempty.name()), e);
		*/}
	}
	private void savePaswordChangeRequest(String hashStringId, String currentTime,Users usersdetail) {
		if(jdbcUserRepository.isExists(usersdetail.getUsername())){
			count++;
			if(jdbcPCRRepository.isExists(usersdetail.getId())){
				PasswordChangeRequestsDTO passwordChangeRequestsDTO =jdbcPCRRepository.getPCRByUserId(usersdetail.getId());
				if(passwordChangeRequestsDTO!=null &&passwordChangeRequestsDTO.getNo_of_attempts()>count){
					jdbcPCRRepository.updatePasswordChangeRequests(
							new PasswordChangeRequestsDTO(
									hashStringId,passwordChangeRequestsDTO.getTime(),
									usersdetail.getId(), 
									passwordChangeRequestsDTO.getNo_of_attempts())
							);	
				}
				else{
					jdbcPCRRepository.updatePasswordChangeRequests(
							new PasswordChangeRequestsDTO(
									hashStringId,
									currentTime,
									usersdetail.getId(), 
									count)
							);
				}
				
			}
			else{
				jdbcPCRRepository.addPCR(
						new PasswordChangeRequestsDTO(
								hashStringId,
								currentTime,
								usersdetail.getId(), 
								count)
						);
				
			}
		}
	}
	public String generateHashShakeyFromString(String randomString) {
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(randomString.getBytes());

	        byte byteData[] = md.digest();

	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

	        System.out.println("Hex format : " + sb.toString());

	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	System.out.println("Hex format : " + hexString.toString());
	    	return hexString.toString();
	    	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
	}
	private void processMail(Users users,String addAccout,String message) {

		mailservice.sendMail("lognsystems@gmail.com", users.getUsername(), addAccout, message);
	}
	public void UpdateAndRetriveUsersAndPCRRecords(PasswordChangeRequests pcrs,String hash_id) throws java.text.ParseException {
		System.out.println("\n\nUpdateAndRetriveUsersAndPCRRecords  pcrs.toString() : " + pcrs.toString());
		
		PasswordChangeRequestsDTO passwordChangeRequestsDTO =jdbcPCRRepository.findPCRByHash_id(hash_id);
		System.out.println("\n\n\n\nUpdateAndRetriveUsersAndPCRRecords  passwordChangeRequestsDTO.toString() : " + passwordChangeRequestsDTO.toString());
		
		
		if(passwordChangeRequestsDTO.getUsers_id()!=0 && passwordChangeRequestsDTO.getUsers_id()>0){
			Users users =new Users();
			
			users.setId(passwordChangeRequestsDTO.getUsers_id());
			
			if(pcrs.getPassword()!=null){
			    
				users.setPassword(CommonUtilities.bCryptPasswordEncoder(pcrs.getPassword()));
			
				System.out.println("\n\n\n\nUpdateAndRetriveUsersAndPCRRecords users users.getPassword() "+users.getPassword());
			
				jdbcUserRepository.updateUserPasswordById(users);
			
				if(passwordChangeRequestsDTO.getNo_of_attempts()>=3){
				count=0;
				jdbcPCRRepository.updatePasswordChangeRequests(
						new PasswordChangeRequestsDTO(
								passwordChangeRequestsDTO.getHash_id(),
								passwordChangeRequestsDTO.getTime(),
								 passwordChangeRequestsDTO.getUsers_id(), 
								count)
						);
			}
			else{
				count++;
				jdbcPCRRepository.updatePasswordChangeRequests(
						new PasswordChangeRequestsDTO(
								passwordChangeRequestsDTO.getHash_id(),
								passwordChangeRequestsDTO.getTime(),
								 passwordChangeRequestsDTO.getUsers_id(), 
								count)
						);
				
			}
			}

		}
	}	
	public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {

	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 	 
		 	  
	    System.out.println("generatedString == "+generatedString);
	    return generatedString;
	}
	public boolean findUsernameIsExist(String username) {
		return jdbcUserRepository.isExists(username);

	}
}
