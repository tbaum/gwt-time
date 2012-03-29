package org.gwttime.time;

import org.gwttime.time.DateTime;
import org.gwttime.time.DateTimeConstants;

public interface ConstantsForTesting {
	
	public static final long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
    									 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
    									 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
    									 366 + 365;
	public static final long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
    									 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
    									 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
    									 366 + 365 + 365;
	public static final long TEST_TIME_NOW = (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
	public static final String TEST_TIME_NOW_STRING = "2002-06-09T00:00:00.000Z";
	public static final long TEST_TIME_NOW_LONDON=TEST_TIME_NOW - DateTimeConstants.MILLIS_PER_HOUR;
	public static final long TEST_TIME_NOW_PARIS=TEST_TIME_NOW_LONDON-DateTimeConstants.MILLIS_PER_HOUR;
	public static final String TEST_TIME_NOW_MIDNIGHT_LONDON_STRING= "2002-06-09T00:00:00.000+01:00";
	public static final String TEST_TIME_NOW_MIDNIGHT_PARIS_STRING="2002-06-09T00:00:00.000+02:00";
	public static final String TEST_TIME_NOW_MIDNIGHT_NEWYORK_STRING="2002-06-08T00:00:00.000-04:00";
	public static final DateTime TEST_TIME_NOW_DATE_TIME = new DateTime(2004,6,9,0,0,0,0);
	
	public static final long TEST_TIME1 =(y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
        									+ 12L * DateTimeConstants.MILLIS_PER_HOUR
        									+ 24L * DateTimeConstants.MILLIS_PER_MINUTE;
	public static final String TEST_TIME1_STRING = "2002-04-05T12:24:00.000Z";
	
	public static final long TEST_TIME2 =(y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
        									+ 14L * DateTimeConstants.MILLIS_PER_HOUR
        									+ 28L * DateTimeConstants.MILLIS_PER_MINUTE;
	public static final String TEST_TIME2_STRING="2003-05-06T14:28:00.000Z";
	
	public static final long TEST_TIME_NOW_NO_2002 =
        (31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
	
	public static final  long TEST_TIME1_NO_2002 =(31L + 28L + 31L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
	        									+ 12L * DateTimeConstants.MILLIS_PER_HOUR
	        									+ 24L * DateTimeConstants.MILLIS_PER_MINUTE;
	public static final long TEST_TIME2_NO_2003 =(365L + 31L + 28L + 31L + 30L + 7L -1L) * DateTimeConstants.MILLIS_PER_DAY
        										+ 14L * DateTimeConstants.MILLIS_PER_HOUR
        										+ 28L * DateTimeConstants.MILLIS_PER_MINUTE;
	
	public static final long TEST_TIME1_LONDON =
        (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
        - DateTimeConstants.MILLIS_PER_HOUR;

	

	public static final long TEST_TIME1_PARIS =
        TEST_TIME1_LONDON- DateTimeConstants.MILLIS_PER_HOUR;
	
	public static final long TEST_TIME2_LONDON =
        (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
         - DateTimeConstants.MILLIS_PER_HOUR;
	
	public static final long TEST_TIME2_PARIS =TEST_TIME2_LONDON- DateTimeConstants.MILLIS_PER_HOUR;
	
}
