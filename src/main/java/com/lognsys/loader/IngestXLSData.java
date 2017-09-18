/**
 * This program is used to populate babycare data from excelsheet. 
 * This used spring-core container for dependency injection of DataSource and Resources like "XLS" . "XML"
 * 
 * @PJD - 06-13-14 Change Log: 07-02-14
 */

package com.lognsys.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.lognsys.util.CommonUtilities;

@Component
public class IngestXLSData implements Ingest {

//	private static Logger log = Logger.getLogger(IngestXLSData.class);

	private Sheet sheet = null;

	private Resource resource;

	// static fields
	private static String ARG = "";

	private static final int EXIT_ERROR = 1;

	private static final int EXIT_OK = 0;

	private static Parser parser = new Parser();

	private int row_count = 0;

	private int row_parsed = 0;

//	@Autowired
//	private JdbcAuditoriumRepository jdbcAuditoriumRepository;

	/**
	 * dependency injection of resource and datasource.
	 * 
	 * @param resource
	 * @param dataSource
	 */
	public IngestXLSData(Resource resource) {
		this.resource = resource;
	}

	// print usage
	public static void usage() {
		System.out.println("Usage : java IngestXLSdata (auditorium | row_seat)");
	}

	/**
	 * This method parses data from excel sheet, builds object from data parsed
	 * from excel sheet loads specific sheet passed as global argument
	 */
	@Override
	public void parseData() {

		Sheet sheet = getSheet();

		String sheetname = sheet.getSheetName();
/*
		switch (EXCELSHEETS.valueOf(sheetname)) {

		case auditorium:
			System.out.println("Parsing Auditoriums Sheet.....");

			List<AuditoriumsDTO> listOfAuditoriums = parser.parseAuditoriums(sheet);

			for (AuditoriumsDTO auditoriumsDTO : listOfAuditoriums) {
				boolean isAdded = jdbcAuditoriumRepository.addAuditoriums(auditoriumsDTO);
				row_parsed++;

				if (isAdded)
					row_count++;
			}

			break;

		case row_seat:
			System.out.println("Note: Please make sure to insert Auditorium Sheet before parsing Row_Seat Sheet..."
					+ "\n Parsing Row_Seat Sheet.....");
			List<RowSeatDTO> listOfRowSeat = parser.parseRowSeat(sheet);
			for (RowSeatDTO rs : listOfRowSeat) {
				String[] range = CommonUtilities.splitByDelemeter(rs.getSeatCount(), "-");
				int start = Integer.parseInt(range[0]);
				int end = Integer.parseInt(range[1]);

				for (int i = start; i <= end; i++) {
					jdbcAuditoriumRepository.addRow_Seat(new RowSeatDTO(rs.getRow_num(), rs.getRow_name(), i,
							jdbcAuditoriumRepository.findIDBy(rs.getAuditorium_name())));
					row_count++;
				}

			}

			break;

		default:
			System.err.println("Sheet not found");
			break;

		}*/

	}

	@Override
	public void printReport() {

		System.out.println("Total Rows Parsed - " + row_parsed);
		System.out.println("Total Rows Inserted - " + row_count);

	}

	/**
	 * run method instantiated
	 */
	@Override
	public int run() {

		// check if the resource injected exists.
		if (!resource.exists()) {
//			log.error("File not found - " + resource.getDescription());
			throw new IllegalArgumentException("ERROR: File not found - " + resource.getDescription());

		}

		// java 7 try-with-resource will close the inputstream after exiting the
		// block-scope
		try (InputStream is = resource.getInputStream()) {
			// Get the workbook instance for XLS file
			Workbook workBook = WorkbookFactory.create(is);

			// if argument then load all the sheets
			if (!IngestXLSData.ARG.isEmpty()) {
				Sheet sheet = workBook.getSheet(IngestXLSData.ARG);

				if (sheet == null) {

					throw new IllegalArgumentException("ERROR :  argument not found... Please see the usage");

				}
				setSheet(sheet);

				parseData();

			}

			printReport();
		} catch (InvalidFormatException | IOException e) {
//			log.error("Error occured while trying to read the file");
			e.printStackTrace();
		}

		return EXIT_OK;
	}

	/**
	 * @return Sheet set from workbook
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * Sets the sheet for parsing
	 * 
	 * @param sheet
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public static void main(String[] args) throws IOException {
		Date date = new Date();

		System.out.println("Kalrav Data Ingest program started - " + date.toString());
		long startMilli = System.currentTimeMillis();

		// checks args.length = 1
		if (args.length != 1) {
			usage();
			System.err.println("Argument required...");
			System.exit(EXIT_ERROR);
		}

		IngestXLSData.ARG = args[0];

		// validate argument with excelsheet name using enum
		boolean isArgValid = false;
		for (EXCELSHEETS sheet : EXCELSHEETS.values()) {
			if (sheet.name().equals(IngestXLSData.ARG)) {
				isArgValid = true;
			}
		}

		if (!isArgValid) {
			usage();
			System.err.println("Invalid Excel file....sheet not found");
			System.exit(EXIT_ERROR);
		}

		// System.setProperty("spring.profiles.active", "dev");

		// Spring Application context loads
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:loader-context.xml" });
		//
		// ctx.getEnvironment().setActiveProfiles("dev");

		// ctx.refresh();

		IngestXLSData ingest = ctx.getBean("xlsResource", IngestXLSData.class);

		// Run ingest using run program
		ingest.run();

		date = new Date();
		long elapsedTimeMillis = System.currentTimeMillis() - startMilli;
		// Get elapsed time in seconds
		float elapsedTimeSec = elapsedTimeMillis / 1000F;

		ctx.close();
		System.out.println();
		System.out.println(
				"Kalrav Ingest program ended " + date.toString() + " Elapsed time = " + elapsedTimeSec + " seconds.");

	}

}
