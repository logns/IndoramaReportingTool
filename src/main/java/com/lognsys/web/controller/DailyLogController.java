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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lognsys.dao.dto.BuDTO;
import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.DailyLog;
import com.lognsys.service.DailyLogService;
import com.lognsys.service.UserService;
import com.lognsys.util.Constants;
import com.lognsys.util.FormValidator;

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
	DataSource conn;
	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/dailylog", method = RequestMethod.GET)
	public String getDailyLogForm(ModelMap model, HttpServletRequest request) {

		String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
		String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
		String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
		String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());

		List<UsersDTO> userDtos=dailyLogService.getRealName();
		List<String> realname = new ArrayList<String>();
		for(int i=0;i<userDtos.size();i++){
			UsersDTO dto=userDtos.get(i);
			realname.add(dto.getRealname());
		}
		System.out.println("controller===== realname- " + realname.size());
		System.out.println("controller===== realname.get(0)- " + realname.get(0) );
		List<BuDTO> listOfBuDTO = userService.getAllBus();

		// Adding data to list from RolesDTO
		List<String> busList = new ArrayList<String>();
		for (BuDTO bu : listOfBuDTO) {
			busList.add(bu.getBu_name());
		}
		List<String> jobtype=Arrays.asList(str_jobtype.split(","));
		List<String> recordtype=Arrays.asList(str_recordtype.split(","));
		List<String> status=Arrays.asList(str_status.split(","));
		List<String> shift=Arrays.asList(str_shift.split(","));
		
		//populate to JSP page
		DailyLog dailylogs = new DailyLog();
		model.addAttribute("dailylogs", dailylogs);
		model.addAttribute("busList", busList);
		model.addAttribute("realname", realname);
		model.addAttribute("jobtype", jobtype);
		model.addAttribute("recordtype", recordtype);
		model.addAttribute("status", status);
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
	public String saveForm(@Valid @ModelAttribute("dailylogs") DailyLog dailylogs, Model model,
		 HttpServletRequest request, BindingResult result,
		HttpServletResponse response) throws JRException, IOException, SQLException, NamingException{
		
		FormValidator formValidator = new FormValidator();
		formValidator.validate(dailylogs, result);

		if (result.hasErrors())
		{
		
			String str_shift = applicationProperties.getProperty(Constants.TYPES_ARRAY.shift.name());
			String str_jobtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.jobtype.name());
			String str_recordtype = applicationProperties.getProperty(Constants.TYPES_ARRAY.recordtype.name());
			String str_status = applicationProperties.getProperty(Constants.TYPES_ARRAY.status.name());

			System.out.println("controller===== str_jobtype- " + str_jobtype.toString());
			System.out.println("controller===== recordtype- " + str_recordtype.toString());
			System.out.println("controller===== str_status- " + str_status.toString());

			List<String> jobtype=Arrays.asList(str_jobtype.split(","));
			List<String> recordtype=Arrays.asList(str_recordtype.split(","));
			List<String> status=Arrays.asList(str_status.split(","));
			List<String> shift=Arrays.asList(str_shift.split(","));
		
			List<UsersDTO> userDtos=dailyLogService.getRealName();
			List<String> realname = new ArrayList<String>();
			for(int i=0;i<userDtos.size();i++){
				UsersDTO dto=userDtos.get(i);
				realname.add(dto.getRealname());
			}
			System.out.println("controller===== realname- " + realname.size());
			System.out.println("controller===== realname.get(0)- " + realname.get(0) );
			List<BuDTO> listOfBuDTO = userService.getAllBus();

			// Adding data to list from RolesDTO
			List<String> busList = new ArrayList<String>();
			for (BuDTO bu : listOfBuDTO) {
				busList.add(bu.getBu_name());
			}
			//populate to JSP page
			model.addAttribute("dailylogs", dailylogs);
			model.addAttribute("realname", realname);
			model.addAttribute("jobtype", jobtype);
			model.addAttribute("recordtype", recordtype);
			model.addAttribute("status", status);
			model.addAttribute("shift", shift);
			return "dailylog";
		} else
		{
			try {
			int id =dailyLogService.addDailyLog(dailylogs);
			System.out.println(" ==================== dailylogs.getJobtype()  "+dailylogs.getJobtype());
			System.out.println(" ==================== dailylogs.getDates()  "+dailylogs.getDates());
			
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
//		dailyLogService.refresDailyListReport();
	
		return "dailylogslist";
	}
	 /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
    	List<DailyLogDTO> lists=  dailyLogService.refresDailyListReport();
	    // return a view which will be resolved by an excel view resolver
    	for(int i=0;i<lists.size();i++){
    		System.out.println("Values  is "+lists.get(i).toString());
    	}
        return new ModelAndView("excelView", "lists", lists);
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
		List<DailyLogDTO> lists=  dailyLogService.refresDailyListReport();
		if(lists== null || lists.size()==0){
			 model.addAttribute("error", "Reports are Not available ");
			return "dailylogslist";
		}
		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		for(int i=0;i<lists.size();i++){
		DailyLogDTO dailylogsdyto=lists.get(i);
		
		hmParams.put("dates", dailylogsdyto.getDates());
		hmParams.put("shift", dailylogsdyto.getShift());
		hmParams.put("bu", dailylogsdyto.getbu_name());
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
		JasperReport jasperReport = dailyLogService.getCompiledFile(reportFileName,
				request);
		 if (reportPdf.equalsIgnoreCase("pdf")) {

			 dailyLogService.generateReportPDF(response, hmParams, jasperReport, c); // For
			
		}
		return null;
    }
}
