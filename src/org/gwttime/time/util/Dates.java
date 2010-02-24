package org.gwttime.time.util;

import java.util.Date;

import org.joda.time.Chronology;
import org.joda.time.ReadablePartial;

public final class Dates {
	
	private Dates(){}
	
	public static int[] getPartialValues(ReadablePartial partial,Date date, Chronology chrono){
		long instant = date.getTime();
		return chrono.get(partial, instant);
	}

}
