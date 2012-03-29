package org.gwttime.time.util;

import java.util.Date;

import org.gwttime.time.DateTimeZone;
import org.gwttime.time.ReadableInstant;
import org.gwttime.time.ReadablePartial;
import org.gwttime.time.tz.GwtTimeZone;
import org.gwttime.time.tz.GwtZoneInfoProvider;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

public class Formatters {

	private Formatters(){}
	private static DateTimeFormat currentFormatter;
	
	public static String stringForPattern(ReadablePartial partial, String pattern){
		org.gwttime.time.format.DateTimeFormatter formatter = org.gwttime.time.format.DateTimeFormat.forPattern(pattern);
		return formatter.print(partial);
	}

	public static DateTimeFormat getFormatter(String pattern) {
		if(currentFormatter==null)
			currentFormatter = DateTimeFormat.getFormat(pattern);
		else if(!currentFormatter.getPattern().equals(pattern))
			currentFormatter = DateTimeFormat.getFormat(pattern);
		
		return currentFormatter;
	}
	
	public static Date dateForPattern(String dateString,String pattern){
		DateTimeFormat formatter = getFormatter(pattern);
		
		return formatter.parse(dateString);
	}
	
	public static String stringForPattern(ReadableInstant instant,String pattern){
		org.gwttime.time.format.DateTimeFormatter formatter = org.gwttime.time.format.DateTimeFormat.forPattern(pattern);
		return formatter.print(instant);
	}
	
	public static String stringForPattern(ReadableInstant instant, String pattern, DateTimeZone displayZone){
		
		TimeZone timeZone = getTimeZone(displayZone);

		DateTimeFormat formatter = getFormatter(pattern);
		return formatter.format(instant.toInstant().toDate(),timeZone);
	}

	private static TimeZone getTimeZone(DateTimeZone displayZone) {
		if(displayZone==null)
			displayZone= DateTimeZone.UTC;
		
		if(displayZone.equals(DateTimeZone.UTC)){
			return null;
		}
		DateTimeZone nextZone = GwtZoneInfoProvider.singleton().getZone(displayZone.getID());
		if(nextZone instanceof GwtTimeZone){
			return ((GwtTimeZone)nextZone).getGwtTimeZone();
		}else
			throw new IllegalArgumentException("No TimeZone matches the specified DateTimeZone ID");
	}
	
}
