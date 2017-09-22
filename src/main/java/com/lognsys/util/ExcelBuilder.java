package com.lognsys.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.lognsys.dao.dto.DailyLogDTO;
import com.lognsys.service.DailyLogService;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 // get data model which is passed by the Spring container
		List<DailyLogDTO> lists=   (List<DailyLogDTO>) model.get("lists");//dailyLogService.refresDailyListReport();
		   
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Java Books");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Dates");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Shift");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Plants");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Substation");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Machine");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Description");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Time From");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("Time To");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("Spare Parts");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("Attend By");
        header.getCell(9).setCellStyle(style);

        header.createCell(10).setCellValue("Job type");
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue("Record type");
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue("Follow Up Status");
        header.getCell(12).setCellStyle(style);
        // create data rows
        int rowCount = 1;
         
        for (DailyLogDTO dailyLogDTO : lists) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(dailyLogDTO.getDates());
            aRow.createCell(1).setCellValue(dailyLogDTO.getShift());
            aRow.createCell(2).setCellValue(dailyLogDTO.getbu_name());
            aRow.createCell(3).setCellValue(dailyLogDTO.getSubstation());
            aRow.createCell(4).setCellValue(dailyLogDTO.getMachine());
            aRow.createCell(5).setCellValue(dailyLogDTO.getDescription());
            aRow.createCell(6).setCellValue(dailyLogDTO.getTimefrom());
            aRow.createCell(7).setCellValue(dailyLogDTO.getTimeto());
            aRow.createCell(8).setCellValue(dailyLogDTO.getSpareparts());
            aRow.createCell(9).setCellValue(dailyLogDTO.getAttendby());
            aRow.createCell(10).setCellValue(dailyLogDTO.getJobtype());
            aRow.createCell(11).setCellValue(dailyLogDTO.getRecordtype());
            aRow.createCell(12).setCellValue(dailyLogDTO.getStatus());
        }
	}
}
