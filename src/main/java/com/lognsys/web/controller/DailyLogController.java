package com.lognsys.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.model.DailyLog;
import com.lognsys.service.DailyLogService;
import com.lognsys.util.Constants;
import com.lognsys.util.FormValidator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;


@Controller
public class DailyLogController {
//private Logger LOG = Logger.getLogger(getClass());
	
	@Autowired
	private DailyLogService dailyLogService;

	
	// Injecting resource application.properties.
		@Autowired
		@Qualifier("applicationProperties")
		private Properties applicationProperties;
	@Autowired
	DataSource conn;	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/dailylog", method = RequestMethod.GET)
	public String getDailyLogForm(ModelMap model, HttpServletRequest request) {
/*
		// CALL database to get roles & groups
		List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
		List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
		List<BuDTO> listOfBuDTO = userService.getAllBus();

		// Adding data to list from RolesDTO
		List<String> rolesList = new ArrayList<String>();
		for (RolesDTO role : listOfRolesDTO) {
			rolesList.add(role.getRole());
		}

		// Adding data to list from RolesDTO
		List<String> busList = new ArrayList<String>();
		for (BuDTO bu : listOfBuDTO) {
			busList.add(bu.getBu_name());
		}
		// Adding data to list from RolesDTO
		List<String> departmentsList = new ArrayList<String>();
		for (DepartmentsDTO deDto : listOfDepartmentsDTO)
		 {
			departmentsList.add(deDto.getDepartment_name());
		}*/
		String realname = "monika sharma";
		String bu = "bu1";
		String substation = "MS1";
		String shift = "Morning";
		String str = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());

		System.out.println("controller===== str- " + str.toString());
		List<String> jobtype=Arrays.asList(str.split(","));

		System.out.println("controller jobtype " + jobtype.size());
		
		//populate to JSP page
		DailyLog dailylogs = new DailyLog();
		model.addAttribute("dailylogs", dailylogs);
		model.addAttribute("bu", bu);
		model.addAttribute("realname", realname);
		model.addAttribute("jobtype", jobtype);
		model.addAttribute("substation", substation);
		model.addAttribute("shift", shift);
		return "dailylog";
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	@RequestMapping(value = "/dailylog", method = RequestMethod.POST)
	public String saveForm(@Valid @ModelAttribute("dailylogs") DailyLog dailylogs, BindingResult result, ModelMap model,
		 HttpServletRequest request,
		HttpServletResponse response) throws JRException, IOException, SQLException, NamingException{
		System.out.println("controller dailylogs to String - " + dailylogs.toString());
//
//		FormValidator formValidator = new FormValidator();
//		formValidator.validate(dailylogs, result);

//		if (result.hasErrors()) {
//			/*System.out.println("Adding Errors - " + user.toString());
//			// CALL database to get roles & groups
//			List<RolesDTO> listOfRolesDTO = userService.getAllRoles();
//			List<DepartmentsDTO> listOfDepartmentsDTO = userService.getAllDepartments();
//			List<BuDTO> listOfBuDTO = userService.getAllBus();
//
//			List<String> rolesList = new ArrayList<String>();
//			for (RolesDTO role : listOfRolesDTO) {
//				rolesList.add(role.getRole());
//			}
//			// Adding data to list from RolesDTO
//			List<String> busList = new ArrayList<String>();
//			for (BuDTO bu : listOfBuDTO) {
//				busList.add(bu.getBu_name());
//			}
//			// Adding data to list from RolesDTO
//			List<String> departmentsList = new ArrayList<String>();
//			for (DepartmentsDTO deDto : listOfDepartmentsDTO) {
//				departmentsList.add(deDto.getDepartment_name());
//			}*/
//
//
//			//populate to JSP page
////			model.addAttribute("rolesList", rolesList);
////			model.addAttribute("departmentsList", departmentsList);
////			model.addAttribute("busList", busList);
//			return "dailylog";
//
////		} else
		{
			try {
			int id =dailyLogService.addDailyLog(dailylogs);
			System.out.println(" ==================== dailylogs.getJobtype()  "+dailylogs.getJobtype());
			System.out.println(" ==================== dailylogs.getDates()  "+dailylogs.getDates());
			
			String reportFileName = "allReports";
			String reportExcel = "xls";
			String reportPdf = "pdf";
			Connection c = conn.getConnection();

		
			List<DailyLogDTO> lists=  dailyLogService.refresDailyListReport();
			
			HashMap<String, Object> hmParams = new HashMap<String, Object>();
			
			for(int i=0;i<lists.size();i++){
			DailyLogDTO dailylogsdyto=lists.get(i);
			
			hmParams.put("dates", dailylogsdyto.getDates());
			hmParams.put("shift", dailylogsdyto.getShift());
			hmParams.put("bu", dailylogsdyto.getBu());
			hmParams.put("substation", dailylogsdyto.getSubstation());
			hmParams.put("machine",dailylogsdyto.getMachine());
			hmParams.put("description",dailylogsdyto.getDescription());
			hmParams.put("timefrom",dailylogsdyto.getTimefrom());
			hmParams.put("timeto",dailylogsdyto.getTimeto());
			hmParams.put("spareparts",dailylogsdyto.getSpareparts());
			hmParams.put("attendby",dailylogsdyto.getAttendby());
			hmParams.put("jobtype",dailylogsdyto.getJobtype());
			hmParams.put("recordtype",dailylogsdyto.getRecordtype());
			hmParams.put("status",dailylogsdyto.getStatus());
			
			System.out.println(" ==================== dailylogsdyto.getJobtype()  "+dailylogsdyto.getJobtype());
			
			}
//			System.out.println("============== generateReport ================hmParams.values() " + hmParams.values());
//			System.out.println("============== generateReport ================hmParams.size() " + hmParams.size());

			JasperReport jasperReport = dailyLogService.getCompiledFile(reportFileName,
					request);
//			System.out.println("============== generateReport ================jasperReport toString " +jasperReport.toString());
//			System.out.println("============== generateReport ================response toString " +response.toString());
//			System.out.println("============== generateReport ================reportPdf.equalsIgnoreCase(pdf) " +(reportPdf.equalsIgnoreCase("pdf")));

			 if (reportPdf.equalsIgnoreCase("pdf")) {

				 dailyLogService.generateReportPDF(response, hmParams, jasperReport, c); // For
				
			}
//			 if (reportExcel.equalsIgnoreCase("pdf")) {
//
//				 dailyLogService.generateReportPDF(response, hmParams, jasperReport, conn); // For
//																					// xls
//																					// report
//
//			 }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "dailylog";
	}
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/dailylogslist", method = RequestMethod.GET)
	public String dailylogslist(Model model, HttpServletRequest request) throws IOException {
		System.out.println("dailylogslist - " );
		dailyLogService.refresDailyListReport();
		return "dailylogslist";
	}
}
