package com.lognsys.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lognsys.dao.dto.AssignTaskDTO;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.exception.CustomGenericException;
import com.lognsys.model.DailyLog;
import com.lognsys.service.DailyLogService;
import com.lognsys.service.UserService;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.FormValidator;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;


@Controller
public class DailyLogController {
	
	@Autowired
	
	private DailyLogService dailyLogService;


	@Autowired
	private UserService userService;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;
	
	@Autowired
	DataSource conn;
	/**
	 * 
	 * send dailylog object to page
	 * @param description,id,assign_task_title
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/adddailylog", method = RequestMethod.GET)
	public String addDailyLogForm(@RequestParam("assign_task_title") String assign_task_title,Model model, HttpServletRequest request) throws IOException {
		
//		System.out.println("\n addDailyLogForm  id \n \n " +id);
		System.out.println("\n addDailyLogForm  assign_task_title \n \n " +assign_task_title);

		assign_task_title =assign_task_title.replace("%20", " ");
	
		
		try {
			DailyLog dailylogs=new DailyLog();
			dailylogs.setAssign_task_title(assign_task_title);
			
			String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
			String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
			String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
			String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());
			String str_priority = applicationProperties.getProperty(Constants.TYPES_ARRAY.priority.name());
			String str_done_percentage = applicationProperties.getProperty(Constants.TYPES_ARRAY.done_percentage.name());
			
			List<BuDTO> listOfBuDTO = userService.getAllBus();
			
			// Adding data to list from BuDTO
			List<String> busList = new ArrayList<String>();
			for (BuDTO bu : listOfBuDTO) {
				busList.add(bu.getBu_name());
			}
			
			List<UsersDTO> listOfUsersDTO = userService.getUsers();
			
			// Adding data to list from RolesDTO
			List<String> usersList = new ArrayList<String>();
			for (UsersDTO user : listOfUsersDTO) {
				usersList.add(user.getRealname());
			}
			populateUsersListInJson(usersList);
			
			List<String> jobtype=Arrays.asList(str_jobtype.split(","));
			List<String> recordtype=Arrays.asList(str_recordtype.split(","));
			List<String> status=Arrays.asList(str_status.split(","));
			List<String> shift=Arrays.asList(str_shift.split(","));
			List<String> priority=Arrays.asList(str_priority.split(","));
			List<String> done_percentage=Arrays.asList(str_done_percentage.split(","));

			model.addAttribute("dailylogs", dailylogs);	
			model.addAttribute("jobtype", jobtype);
			model.addAttribute("recordtype", recordtype);	
			model.addAttribute("status", status);
			model.addAttribute("shift", shift);
			model.addAttribute("priority", priority);
			model.addAttribute("done_percentage", done_percentage);
			model.addAttribute("busList", busList);
			model.addAttribute("usersList", usersList);
		} catch (Exception e) {
			System.out.println("\n Exception addDailyLogForm \n \n " +e.toString());
			
			e.printStackTrace();
		}
	
		return "adddailylog";
	}

	private void populateUsersListInJson(List<String> usersList) {

		
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.realname_filename.name()));
		String list = CommonUtilities.convertToJSON(usersList);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n Exception populateUsersListInJson \n \n " +e.toString());
			throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
		
		}
	}

	/**
	 * 
	 * Saving  dailylog
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	@RequestMapping(value = "/adddailylog", method = RequestMethod.POST)
	public String saveForm(@Valid @ModelAttribute("dailylogs") DailyLog dailylogs, Model model,
		 HttpServletRequest request, BindingResult result,
		HttpServletResponse response) throws JRException, IOException, SQLException, NamingException{
		System.out.println("\n \n saveForm dailylogs == "+dailylogs.toString()+"\n \n");
		
		try {
			FormValidator formValidator = new FormValidator();
			formValidator.validate(dailylogs, result);
			if(dailylogs.getBu().equalsIgnoreCase("None")){
				
				if (result.hasErrors() ||(dailylogs.getBu().equalsIgnoreCase("None")) || (dailylogs.getBu().equalsIgnoreCase("select")))
				{
				
					String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
					String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
					String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
					String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());

					List<String> jobtype=Arrays.asList(str_jobtype.split(","));
					List<String> recordtype=Arrays.asList(str_recordtype.split(","));
					List<String> status=Arrays.asList(str_status.split(","));
					List<String> shift=Arrays.asList(str_shift.split(","));
					List<UsersDTO> listOfUsersDTO = userService.getUsers();
					
					List<String> usersList = new ArrayList<String>();
					for (UsersDTO user : listOfUsersDTO) {
						usersList.add(user.getRealname());
					}
					populateUsersListInJson(usersList);
										
					List<BuDTO> listOfBuDTO = userService.getAllBus();

					// Adding data to list from RolesDTO
					List<String> busList = new ArrayList<String>();
					for (BuDTO bu : listOfBuDTO) {
						busList.add(bu.getBu_name());
					}
					//populate to JSP page
					model.addAttribute("dailylogs", dailylogs);
					model.addAttribute("usersList", usersList);
					model.addAttribute("jobtype", jobtype);
					model.addAttribute("recordtype", recordtype);
					model.addAttribute("status", status);
					model.addAttribute("shift", shift);;
					model.addAttribute("busList", busList);
					return "adddailylog";
				}
			}
			else
			{

				AssignTaskDTO dto=jdbcAssignTaskRepository.findAssignTaskDTOTitlte(dailylogs.getAssign_task_title());
				dailylogs.setAssign_task_id(dto.getId());
				
				int id =dailyLogService.addDailyLog(dailylogs);
				dailylogs.setId(id);
				
				if(dailylogs.getStatus().equalsIgnoreCase("closed") && dailylogs.getDone_percentage().equalsIgnoreCase("100%")){
				dailyLogService.deleteDailyLogs(dailylogs.getAssign_task_id());	
				}
			}

			return "assigntasklist";
		} catch (IndexOutOfBoundsException e) {
		
			System.out.println("\n IndexOutOfBoundsException saveForm \n \n " +e.toString());
				e.printStackTrace();
			return "adddailylog";
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("\n DataAccessException saveForm \n \n " +e.toString());
			throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.data_access_exception.name()));	
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\n IOException saveForm \n \n " +e.toString());
			throw new CustomGenericException(applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.something_went_wrong.name()));	
		}
	}

	 /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
    /*	List<DailyLogDTO> lists=  dailyLogService.refresDailyListReport();
	    // return a view which will be resolved by an excel view resolver
    	for(int i=0;i<lists.size();i++){
    		System.out.println("Values  is "+lists.get(i).toString());
    	}
    */    return null;//new ModelAndView("excelView", "lists", lists);
    } /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
    public String downloadPdf(Model model,
    		HttpServletRequest request,
    		HttpServletResponse response) throws JRException, IOException, SQLException, NamingException{
    
		String reportFileName = "reports_landscape";
		String reportPdf = "pdf";
		Connection c = conn.getConnection();
		/*List<DailyLogDTO> lists=  dailyLogService.refresDailyListReport();
		if(lists== null || lists.size()==0){
			 model.addAttribute("error", "Reports are Not available ");
			return "dailylogslist";
		}
		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		for(int i=0;i<lists.size();i++){
		DailyLogDTO dailylogsdyto=lists.get(i);
		
//		hmParams.put("dates", dailylogsdyto.getDates());
		hmParams.put("shift", dailylogsdyto.getShift());
//		hmParams.put("bu", dailylogsdyto.getbu_name());
		hmParams.put("machine",dailylogsdyto.getMachine());
		hmParams.put("description",dailylogsdyto.getDescription());
		hmParams.put("timefrom",dailylogsdyto.getTimefrom());
		hmParams.put("timeto",dailylogsdyto.getTimeto());
//		hmParams.put("spareparts",dailylogsdyto.getSpareparts());
		hmParams.put("attendby",dailylogsdyto.getAttendby());
		hmParams.put("jobtype",dailylogsdyto.getJobtype());
		hmParams.put("recordtype",dailylogsdyto.getRecordtype());
		hmParams.put("status",dailylogsdyto.getStatus());
		
		System.out.println(" ==================== dailylogsdyto.getJobtype()  "+dailylogsdyto.getJobtype());
		
		}
		JasperReport jasperReport = dailyLogService.getCompiledFile(reportFileName,
				request);
		 if (reportPdf.equalsIgnoreCase("pdf")) {

			 dailyLogService.generateReportPDF(response, hmParams, jasperReport, c); // For
			
		}
*/		return null;
    }
	/**
	 * showList of Dailylogs
	 * @param title
	 * @param request
	 * @return
	 * @throws IOException
	 */
    
	@RequestMapping(value = "/dailyloglist", method = RequestMethod.GET)
	public String showList(@RequestParam("title") String title,Model model, HttpServletRequest request) throws IOException {
		
		title =title.replace("%20", " ");
		dailyLogService.fetchDailyLog(title);
		return "dailyloglist";
	}
	
}
