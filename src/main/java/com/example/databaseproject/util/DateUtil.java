package com.example.databaseproject.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private DateUtil() {
	}

	public static LocalDate today() {
		return LocalDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalDate();
	}

	public static LocalTime now() {
		return LocalDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalTime();
	}

	public static LocalDateTime todayDateTime() {
		return LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
	}

	public static String getFormattedDate() {
		return (DateUtil.today()).format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}

	public static String DateFormattedDateTime(LocalDate date, LocalTime time) {
		return LocalDateTime.of(date, time).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
	}
	public static String DateFormattedDateTime1(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
	}

}
