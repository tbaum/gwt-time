package org.gwttime.time.compare;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.chrono.ISOChronology;

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
