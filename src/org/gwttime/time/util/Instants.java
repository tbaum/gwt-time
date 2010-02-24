package org.gwttime.time.util;

import org.joda.time.Chronology;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public final class Instants {
	
	private Instants(){}
	
	public static int[] getPartialValues(ReadablePartial partial, ReadableInstant instant,Chronology chrono){
		long inst = instant.getMillis();
		return chrono.get(partial, inst);
	}

}
