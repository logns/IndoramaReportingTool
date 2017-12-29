package com.lognsys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	String currentdate,  databaseDate;
	
	  public DateTimeUtils() {
		super();
		
	}

	//1 minute = 60 seconds
	//1 hour = 60 x 60 = 3600
	//1 day = 3600 x 24 = 86400
	public String printDifference(String databaseDate , String currentdate){
		 Date date1 = null;
		 Date date2 = null;
		try {
			
			  SimpleDateFormat simpleDateFormat =
		                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			 date1 = simpleDateFormat.parse(databaseDate );
			 date2 = simpleDateFormat.parse(currentdate );

		  } catch (ParseException e) {
			e.printStackTrace();
		  }
			Date startDate =date1;
			Date endDate= date2;

		
		//milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : "+ endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf(
		    "%d days, %d hours, %d minutes, %d seconds%n",
		    elapsedDays,
		    elapsedHours, elapsedMinutes, elapsedSeconds);
		String value=elapsedDays+" days, "+elapsedHours+" hours, " +elapsedMinutes+ " minutes, "+elapsedSeconds+" seconds";
		return (value);
	}
}