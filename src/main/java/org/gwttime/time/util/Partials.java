package org.gwttime.time.util;

import org.gwttime.time.Chronology;
import org.gwttime.time.ReadablePartial;

public final class Partials {
	
	private Partials(){}
	
	public static int[] getPartialValues(ReadablePartial source,ReadablePartial destination, Chronology chrono){
		int [] values = new int[source.size()];
		fillValues(source, destination, values);
		chrono.validate(source,values);
		return values;
	}

	private static void fillValues(ReadablePartial source,
			ReadablePartial destination, int[] values) {
		for(int i=0;i<values.length;i++){
			values[i] = source.get(destination.getFieldType(i));
		}
	}
	
	public static int[] getCheckedPartialValues(ReadablePartial source, ReadablePartial dest, Chronology chrono){
		int sourceSize = source.size();
		int destSize = dest.size();
		int [] values;
		if(destSize<sourceSize){
			values = new int[destSize];
		}else{
			values = new int[sourceSize];
		}
		fillValues(source,dest,values);
		return values;
	}	
	

}
