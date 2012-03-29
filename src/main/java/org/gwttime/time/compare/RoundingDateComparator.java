package org.gwttime.time.compare;

import java.util.Date;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.chrono.ISOChronology;

public final class RoundingDateComparator<T extends Date> extends RoundingJodaComparator<T> {

	protected RoundingDateComparator(DateTimeFieldType lowerLimit,
			DateTimeFieldType upperLimit) {
		super(lowerLimit, upperLimit);
	}
	
	public static <T extends Date> RoundingDateComparator<T> getComparator(DateTimeFieldType lowerLimit,DateTimeFieldType upperLimit){
		return new RoundingDateComparator<T>(lowerLimit, upperLimit);
	}

	@Override
	protected Chronology getChronology(T element) {
		return ISOChronology.getInstance();
	}

	@Override
	protected long getMillis(T element) {
		return element.getTime();
	}

}
