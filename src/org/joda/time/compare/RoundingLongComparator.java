package org.joda.time.compare;

import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.chrono.ISOChronology;

public final class RoundingLongComparator extends RoundingJodaComparator<Long> {

	protected RoundingLongComparator(DateTimeFieldType lowerLimit,
			DateTimeFieldType upperLimit) {
		super(lowerLimit, upperLimit);
	}
	
	public static RoundingLongComparator getComparator(DateTimeFieldType lowerLimit,DateTimeFieldType upperLimit){
		return new RoundingLongComparator(lowerLimit, upperLimit);
	}

	@Override
	protected Chronology getChronology(Long element) {
		return ISOChronology.getInstance();
	}

	@Override
	protected long getMillis(Long element) {
		return element;
	}

}
