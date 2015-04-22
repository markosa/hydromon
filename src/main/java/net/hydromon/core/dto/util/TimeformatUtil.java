package net.hydromon.core.dto.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeformatUtil {

	public static final String DEFAULT_FORMAT = "HH:mm:ss dd/MM/yyyy";
	public static final SimpleDateFormat DEFAULT_FORMATTER = new SimpleDateFormat(DEFAULT_FORMAT);

	public static String formatDate(Long timeInMillis) {
		return DEFAULT_FORMATTER.format(new Date(timeInMillis));
	}
	
	public static String formatDate(Date date) {
		return DEFAULT_FORMATTER.format(date);
	}
	
	public static String formatDate(Timestamp ts) {
		return DEFAULT_FORMATTER.format(ts);
	}
	
	
	public static String formatDate(Long timeInMillis, String format) {
		return new SimpleDateFormat(format).format(new Date(timeInMillis));
	}
	
}
