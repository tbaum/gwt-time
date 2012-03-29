package org.gwttime.time.format;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.ReadablePartial;

import com.google.gwt.i18n.client.LocaleInfo;

public class GwtDateTimeFormatter implements DateTimePrinter,DateTimeParser {
	private final com.google.gwt.i18n.client.DateTimeFormat dtFormat;
	
	
	public GwtDateTimeFormatter(com.google.gwt.i18n.client.DateTimeFormat dtFormat) {
		this.dtFormat = dtFormat;
	}
	
	public static GwtDateTimeFormatter forPattern(String pattern){
		if(pattern==null||pattern.length()<=0)
			throw new IllegalArgumentException("Pattern cannot be null");
		return new GwtDateTimeFormatter(com.google.gwt.i18n.client.DateTimeFormat.getFormat(pattern));
	}

	@Override
	public int estimatePrintedLength() {
		return dtFormat.getPattern().length();
	}

	@Override
	public void printTo(StringBuffer buf, long instant, Chronology chrono,
			int displayOffset, DateTimeZone displayZone, LocaleInfo locale) {
		
		
	}

	@Override
	public void printTo(StringBuffer buf, ReadablePartial partial,
			LocaleInfo locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int estimateParsedLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int parseInto(DateTimeParserBucket bucket, String text, int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
