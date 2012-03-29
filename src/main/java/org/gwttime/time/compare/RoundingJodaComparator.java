package org.gwttime.time.compare;


import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeFieldType;

/**
 * <p>A Comparator of type which performs rounding on the values 
 * specified by lowerLimit and upperLimit.</p>
 * 
 * @author Scott Fines(sf)
 *
 * @param <T>
 */
public abstract class RoundingJodaComparator<T> extends JodaComparator<T> {
	private final DateTimeFieldType lowerLimit;
	private final DateTimeFieldType upperLimit;
	
	/**
	 * <p>If both lowerLimit and upperLimit are null, then no rounding will occur(sf).</p>
	 * @param lowerLimit
	 * @param upperLimit
	 */
	protected RoundingJodaComparator(DateTimeFieldType lowerLimit, DateTimeFieldType upperLimit){
		this.lowerLimit=lowerLimit;
		this.upperLimit=upperLimit;
	}
	
	@Override
	protected final long getMilliseconds(T element) {
		Chronology chron = getChronology(element);
		long millis = getMillis(element);
		if(lowerLimit!=null)
			millis = lowerLimit.getField(chron).roundFloor(millis);
		if(upperLimit!=null)
			millis = upperLimit.getField(chron).remainder(millis);
		
		
		return millis;
	}

	protected abstract Chronology getChronology(T element);
	
	protected abstract long getMillis(T element);

}
