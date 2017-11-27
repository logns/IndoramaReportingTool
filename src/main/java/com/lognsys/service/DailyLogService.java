package com.lognsys.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAssignTaskRepository;
import com.lognsys.dao.jdbc.JdbcBuRepository;
import com.lognsys.dao.jdbc.JdbcDailyLogRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.DailyLog;
import com.lognsys.model.DailylogTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@Service("dailylogService")
public class DailyLogService {

	@Autowired
	private JdbcDailyLogRepository jdbcDailyLogRepository;

	@Autowired
	private JdbcAssignTaskRepository jdbcAssignTaskRepository;

	@Autowired
	private JdbcBuRepository jdbcBuRepository;
	@Autowired
	private JdbcUserRepository jdbcUserRepository;

	// Injecting resource application.properties.
	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * Add dailylog to database.. 
	 * 
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public int addDailyLog(DailyLog dailyLog) throws IOException {

		// convert dailyLog -> dailyLogDTO Object
		DailyLogDTO dailyLogDTO = ObjectMapper.mapToDailyLogDTO(dailyLog);
		
		int dailylog_id = jdbcDailyLogRepository.addDailyLog(dailyLogDTO);
		
//		adding assigntask_dailylog table with  id of dailylog and assigntask
		int assignDailyLog_id = jdbcAssignTaskRepository.addAssignTask_DailyLog(dailyLogDTO.getAssign_task_id(),
				dailylog_id);
		
		if (dailylog_id > 0 && dailyLogDTO.getBu() != null && dailyLogDTO.getBu().length() > 0) {
			int bu_id = jdbcBuRepository.findBuByName(dailyLogDTO.getBu());
			if (bu_id > 0 && bu_id != 0) {

//				adding DailyLogAndBu table with  id of dailylog and bu_id
				jdbcDailyLogRepository.addDailyLogAndBu(dailylog_id, bu_id);
			}
		}
		try {
//			fetch DailyLog by title
			fetchDailyLog(dailyLog.getAssign_task_title());
		} catch (IOException io) {
			System.out.println("Rest addDailyLog  readAssignTask - " + io.getMessage());
		}

		return dailylog_id;
	}

//	public List<DailyLogDTO> refresDailyListReport() {
//		List<DailyLogDTO> lists = (jdbcDailyLogRepository.getAllDailyLogDTO());
//
//		ResourceLoader resourceLoader = new FileSystemResourceLoader();
//		Resource resource = resourceLoader
//				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.dailylogs_filename.name()));
//		String list = CommonUtilities.convertToJSON(lists);
//
//		try {
//			WriteJSONToFile.getInstance().write(resource, list);
//			return lists;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return lists;
//	}

	public List<UsersDTO> getRealName() {

		List<UsersDTO> realnames;

		try {
			realnames = jdbcUserRepository.getUserRealNames();
		} catch (DataAccessException dae) {
			// LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to find username!");
		}
		return realnames;
	}

	public JasperReport getCompiledFile(String reportFileName, HttpServletRequest request)
			throws JRException, IOException {
		Resource banner = resourceLoader.getResource("classpath:report/reports_landscape.jasper");
		Resource bannerjrxml = resourceLoader.getResource("classpath:report/reports_landscape.jrxml");

		// System.out.println("===========resourceLoader "
		// +banner.getFile().getAbsolutePath());
		// System.out.println("===========resourceLoader bannerjrxml "
		// +bannerjrxml.getFile().getAbsolutePath());
		File reportFile = new File(banner.getFile().getAbsolutePath());

		System.out.println("\n ===========resourceLoader reportFile " + reportFile);
		System.out.println("\n ===========resourceLoader reportFile.exists() " + reportFile.exists());

		// If compiled file is not found, then compile XML template
		if (!reportFile.exists()) {
			try {
				JasperCompileManager.compileReportToFile(bannerjrxml.getFile().getAbsolutePath(),
						banner.getFile().getAbsolutePath());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("\n =========== reportFile JRLoader " + JRLoader.loadObjectFromFile(reportFile.getPath()));

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		System.out.println("\n ===========resourceLoader jasperReport " + jasperReport);
		return jasperReport;
	}

	public void generateReportPDF(HttpServletResponse response, HashMap<String, Object> parameters,
			JasperReport jasperReport, Connection conn) throws JRException, NamingException, SQLException, IOException {
		byte[] bytes = null;
		System.out.println("\n ===========generateReportPDF parameters size " + parameters.size());
		System.out.println("\n ===========generateReportPDF parameters values " + parameters.values());

		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
		System.out.println("\n ===========generateReportPDF bytes " + bytes);

		response.reset();
		response.resetBuffer();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}

	public List<DailyLogDTO> fetchDailyLog(String title) throws IOException {

		List<DailyLogDTO> lists = jdbcDailyLogRepository.getDailyLogDTOByTitle(title);
		List<DailylogTable> dailylogTables = null;

		if (lists != null && lists.size() > 0) {
			dailylogTables = ObjectMapper.mapToDailyLogTable(lists);
		}

		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.dailylogs_filename.name()));
		String list = CommonUtilities.convertToJSON(dailylogTables);

		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			System.out.println("IOEXCEPTION --- e" + e);
			e.printStackTrace();
		}

		return lists;
	}

//	get Dailylog dto by  description and id
	public DailyLogDTO getDailLogbyDescriptionAndId(String description, int id) {
		DailyLogDTO dailyLogDTO = (jdbcDailyLogRepository.findDailyLogDTOByIdAndDescription(description, id));
		return dailyLogDTO;
	}
	
//	delete Dailylog by assigntaskid
	public void deleteDailyLogs( int assign_task_id) {
		jdbcDailyLogRepository.deleteDailyLog(assign_task_id);
	}

}
