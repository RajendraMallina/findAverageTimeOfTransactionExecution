package com.techmojo.transaction.problem.builder;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is responsible to do the all Date and Time related operation 
 * which helps to calculate the average time of the transaction execution.
 * 
 * @author Rajendra Mallina
 *
 */
public class DateAndTimeOperations {

	
	private static final String DATE_FORMAT = "yyyy–MM–dd hh:mm aa";
	private static final double MILLI_TO_HOUR = 1000 * 60 * 60;
	
	/**
	 * This method will return the milliseconds between the two dates.
	 * @param startTime - Starting date
	 * @param endTime - Ending date
	 * @return - Milliseconds between start and end Time.
	 */
	public Double getHoursBetweenDates(String startTime, String endTime) {
		
		SimpleDateFormat targetFormat = new SimpleDateFormat(DATE_FORMAT);
		double timeInMilliSeconds = 0;	
		
		try {
			
			Date startDate = targetFormat.parse(startTime);
			Date endDate = targetFormat.parse(endTime);
			
			timeInMilliSeconds = (endDate.getTime() - startDate.getTime());
			//System.out.println(timeInMilliSeconds);
			
			
		} catch (ParseException e) {
			
			System.out.println(e.getMessage());
		}	
		return timeInMilliSeconds;
	}
	
	/**
	 * This method will convert the milliseconds to hours.
	 * @param milliSeconds - Milliseconds.
	 * @return - Converted Hours from given MilliSeconds(till 2 decimal points).
	 */
	public String convertMilliSecondsToHours(double milliSeconds) {
		
		return String.format("%.2f",(milliSeconds / MILLI_TO_HOUR));
		
	}

}

