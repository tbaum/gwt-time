package org.gwttime.time.compare;

import java.util.Comparator;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.DateTimeUtils;
import org.gwttime.time.ReadableInstant;

public final class ReadableInstantComparator<T extends ReadableInstant> extends RoundingJodaComparator<T> {
	
	private ReadableInstantComparator(DateTimeFieldType lowerLimit,
			DateTimeFieldType upperLimit) {
		super(lowerLimit, upperLimit);
	}
	
	public static <T extends ReadableInstant> Comparator<T> 
					getComparator(DateTimeFieldType lowerLimit, DateTimeFieldType upperLimit){
		return new ReadableInstantComparator<T>(lowerLimit, upperLimit);
	}

	@Override
	protected Chronology getChronology(T element) {
		return DateTimeUtils.getChronology(element.getChronology());
	}

	@Override
	protected long getMillis(T element) {
		return element.getMillis();
	}

	
}
