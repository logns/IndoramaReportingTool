package com.lognsys.controller;

/**
 * Description : This is Base controller which serves the handling of 
 * login, users request and responds to appropriate view.
 * 
 * NOTE : No global variable should be defined!!!
 * 
 * Default: Spring mvc Controller is Singleton Class. 
 * 
 * @author pdoshi
 * 
 */

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;
import com.lognsys.service.UserService;
import com.lognsys.util.FormValidator;
import com.lognsys.web.controller.BaseController;


@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBaseController {
	
	private MockMvc mockMvc;
/*

	@Mock //Mokito Mock Object
	@Autowired
    private UserService userService;
    
    @InjectMocks
    private BaseController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }
    
    @Test
    public void test_create_success() throws Exception {
		 Users users=new Users();
	     users.setAuth_id("1234");
	     users.setAddress("Malad");
	     users.setBirthdate("1991-05-13");
	     users.setCity("Mumbai");
	     users.setDevice("Xml1234");
	     users.setEnabled(true);
	     users.setFirstname("reena");
	     users.setGroup("JJC");
	     users.setlastname("More");
	     users.setNotification(true);
	     users.setPhone("7788998877");
	     users.setProvenance("Android");
	     users.setRealname("Mana More");
	     users.setRole("GUEST");
	     users.setState("Maharashtra");
	     users.setUsername("Maharashtra@gmail.com");
	     users.setZipcode("401107");
	     
	     userService.addUser(users);
	     System.out.println("thenReturn userService.exists(users) "+userService.exists(users));
			
	     when(userService.exists(users)).thenReturn(true);

	     System.out.println("thenReturn userService.exists(users) ==============="+userService.exists(users));
	     when(userService.exists(users)).thenThrow(IllegalStateException.class);

			
		assertThat(users, is(equalTo(users)));
		
	     when(userService.addUser(users)).thenReturn(1);
    }
    

    @Test
    public void test_CRUD() throws Exception {
    	 Users users=new Users();
    	 users.setId(1);
	     users.setAuth_id("1234");
	     users.setAddress("Malad");
	     users.setBirthdate("1991-05-13");
	     users.setCity("Mumbai");
	     users.setDevice("Xml1234");
	     users.setEnabled(true);
	     users.setFirstname("A");
	     users.setGroup("JJC");
	     users.setlastname("More");
	     users.setNotification(true);
	     users.setPhone("7788998877");
	     users.setProvenance("Android");
	     users.setRealname("A More");
	     users.setRole("GUEST");
	     users.setState("Maharashtra");
	     users.setUsername("a@gmail.com");
	     users.setZipcode("401107");
		
		

	 	 Users users1=new Users();
    	 users1.setId(2);
	     users1.setAuth_id("1234");
	     users1.setAddress("Malad");
	     users1.setBirthdate("1991-05-13");
	     users1.setCity("Mumbai");
	     users1.setDevice("Xml1234");
	     users1.setEnabled(true);
	     users1.setFirstname("B");
	     users1.setGroup("JJC");
	     users1.setlastname("More");
	     users1.setNotification(true);
	     users1.setPhone("7788998877");
	     users1.setProvenance("Android");
	     users1.setRealname("B More");
	     users1.setRole("GUEST");
	     users1.setState("Maharashtra");
	     users1.setUsername("m@gmail.com");
	     users1.setZipcode("401107");
		
		
	 	 Users users2=new Users();
    	 users2.setId(3);
	     users2.setAuth_id("1234");
	     users2.setAddress("Malad");
	     users2.setBirthdate("1991-05-13");
	     users2.setCity("Mumbai");
	     users2.setDevice("Xml1234");
	     users2.setEnabled(true);
	     users2.setFirstname("C");
	     users2.setGroup("JJC");
	     users2.setlastname("More");
	     users2.setNotification(true);
	     users2.setPhone("7788998877");
	     users2.setProvenance("Android");
	     users2.setRealname("C More");
	     users2.setRole("GUEST");
	     users2.setState("Maharashtra");
	     users2.setUsername("b@gmail.com");
	     users2.setZipcode("401107");
		
		
		UsersDTO dto=com.lognsys.util.ObjectMapper.mapToUsersDTO(users);
		UsersDTO dto2=com.lognsys.util.ObjectMapper.mapToUsersDTO(users1);
		UsersDTO dto3=com.lognsys.util.ObjectMapper.mapToUsersDTO(users2);
		
//		READ ALL UsersDTO=====
		when(userService.getUsers()).thenReturn(Arrays.asList(dto, dto2,dto3));
		System.out.println(Arrays.asList(dto, dto2,dto3));
		

//		FIND a UsersDTO BY ID ,USERNAME or DEVICE =====
		String response=asJson();
		System.out.println("responce "+response);
		
		when(userService.getUserWithRoleAndGroup(response)).thenReturn(users2);
		System.out.println("thenReturn by response users2 "+users2);
		
		when(userService.getUserWithRoleAndGroup(1)).thenReturn(users);
		System.out.println("thenReturn by 1  users "+users);
		
		when(userService.getUserByUsername("x@gmail.com")).thenThrow(RuntimeException.class);
		System.out.println("thenReturn by 1  x@gmail.com Runtime exception ");
		
		when(userService.getUserByUsername("m@gmail.com")).thenReturn(users1);
		System.out.println("thenReturn by m@gmail.com users1 "+users1);
		

//		Create a UsersDTO BY=====
		when(userService.addUser(users1)).thenReturn(users1.getId());
		System.out.println("Create a UsersDTO by users1  "+users1.getId());
		when(userService.addUser(users2)).thenReturn(3);
		
		
//		UPDATE a UsersDTO BY ID 1 and UsersDTO obje=====
		users2.setRealname("Savior Sales");
		
		when(userService.updateUser(users2)).thenReturn(true);
		System.out.println("UPDATE a UsersDTO by  users2  "+users2.toString());
		
		
			
//		exists a UsersDTO =====
		when(userService.exists(users2)).thenReturn(true);
		System.out.println("exists a UsersDTO userService.exists(users2)  "+ userService.exists(users2));
		
		

//		FIND a DRAMA BY TITLE=====
		when(userService.getUserWithRoleAndGroup(3)).thenReturn(users2);
		
		
		

* DELETE

	 try {
		 userService.addUser(users);
		 userService.addUser(users1);	
		 userService.addUser(users2);	

			List<Users> lists=new ArrayList<Users>();
			
				lists.add(users);
				lists.add(users1);
//				lists.add(users2);
				
				Integer[] arryIds =new Integer[lists.size()];
				for(int i=0;i< lists.size();i++){
					arryIds[i]=lists.get(i).getId();
					
				}

			    when(userService.deleteUsers(arryIds)).thenReturn(true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		 
    }
    public String asJson(){
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("username", "b@gmail.com");
    	jsonObject.put("device", "Xml1234");
    	return jsonObject.toString();
    }
    */
}