package org.gwttime.time.util;

import org.gwttime.time.Chronology;
import org.gwttime.time.ReadableInstant;
import org.gwttime.time.ReadablePartial;

public final class Instants {
	
	private Instants(){}
	
	public static int[] getPartialValues(ReadablePartial partial, ReadableInstant instant,Chronology chrono){
		long inst = instant.getMillis();
		return chrono.get(partial, inst);
	}

}
