package com.mytest.billapp.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyClasee {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		MyClasee mc = new MyClasee();
		mc.getOnePlusHrTime("14:30 PM");
		//mc.dateTest();

	}
	
	//Input: 4:00 PM
	//Expected Output: 4:00-5:00 PM
	private String getOnePlusHrTime(String timeStr) {
		String time = timeStr.split(" ")[0];
		// Convert given text time to java LocalTime object
		LocalTime specificTime = LocalTime.of(Integer.parseInt(time.split(":")[0]),Integer.parseInt(time.split(":")[1]),00,00);
		DateTimeFormatter timeFormatter3 = DateTimeFormatter .ofPattern("hh:mm:ss a");
		specificTime.format(timeFormatter3);
		
		System.out.println("specificTime "+specificTime);
		// Adding 1 hour to given date
		String timePlusHr = specificTime.plusHours(1).toString();
		
		
		
		// Format hour to single digit if hour is < 10
		String formattedTime = Integer.parseInt(timePlusHr.split(":")[0])+":"+timePlusHr.split(":")[1];
		// Formatting required time format
		String newTimeStr = timeStr.split(" ")[0]+"-"+formattedTime+" "+timeStr.split(" ")[1];
		System.out.println("Specific Time of Day===  "+newTimeStr);
		return newTimeStr;
		
	}
	
	private void dateTest() {
		try {
			String dateStr = "19/04/2018";
			System.out.println("Date object "+sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
