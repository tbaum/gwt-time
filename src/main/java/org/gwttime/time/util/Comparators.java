package org.gwttime.time.util;

import java.util.Comparator;
import java.util.Date;

import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.ReadableInstant;
import org.gwttime.time.compare.ReadableInstantComparator;
import org.gwttime.time.compare.RoundingDateComparator;
import org.gwttime.time.compare.RoundingLongComparator;


public class Comparators {
	
	private Comparators(){}
	
	public static final Comparator<ReadableInstant> readableInstantComparator = new Comparator<ReadableInstant>(){
			
			@Override
			public int compare(ReadableInstant instant1,ReadableInstant instant2){
				if(instant1==null){
					if(instant2==null)return 0;
					return -1;
				}else if(instant2==null)return 1;
				
				return instant1.compareTo(instant2);
			}
		};
		
//	public static final Comparator<ReadablePartial> readablePartialComparator =new Comparator<ReadablePartial>(){
//		
//		@Override
//		public int compare(ReadablePartial partial1,ReadablePartial partial2){
//			if(partial1==null){
//				if(partial2==null)return 0;
//				return -1;
//			}else if(partial2==null)return 1;
//			
//			return partial1.compareTo(partial2);
//		}
//	}; 
		
	public static <T extends ReadableInstant> Comparator<T> getRoundingComparator(DateTimeFieldType lower, DateTimeFieldType upper){
		return ReadableInstantComparator.getComparator(lower, upper);
	}
	
	public static <T extends ReadableInstant> Comparator<T> getRoundingComparator(DateTimeFieldType lower){
		return ReadableInstantComparator.getComparator(lower,null);
	}
	
	public static <T extends Date> Comparator<T> getDateComparator(DateTimeFieldType lower, DateTimeFieldType upper){
		return RoundingDateComparator.getComparator(lower, upper);
	}

	public static <T extends Date> Comparator<T> getDateComparator(DateTimeFieldType lower){
		return RoundingDateComparator.getComparator(lower,null);
	}
	
	public static Comparator<Long> getLongComparator(DateTimeFieldType lower,DateTimeFieldType upper){
		return RoundingLongComparator.getComparator(lower, upper);
	}
	
	public static Comparator<Long> getLongComparator(DateTimeFieldType lowerBound){
		return RoundingLongComparator.getComparator(lowerBound, null);
	}
}
