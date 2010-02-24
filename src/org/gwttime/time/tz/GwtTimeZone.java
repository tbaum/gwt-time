package org.gwttime.time.tz;

import java.util.Date;

import org.gwttime.time.DateTimeConstants;
import org.gwttime.time.DateTimeZone;

import com.google.gwt.i18n.client.TimeZone;

public class GwtTimeZone extends DateTimeZone {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TimeZone gwtTimeZone;

    public GwtTimeZone(TimeZone gwtTimeZone) {
        super(gwtTimeZone.getID());
        this.gwtTimeZone = gwtTimeZone;
    }

	public TimeZone getGwtTimeZone() {
        return gwtTimeZone;
    }

    @SuppressWarnings("deprecation")
	public long previousTransition(long instant) {
    	Date origDate = new Date(instant);
    	//go backwards by one month until offset changes.
    	int originalOffset = gwtTimeZone.getOffset(origDate);
    	int originalYear = origDate.getYear();
    	int originalMonth = origDate.getMonth();
    	int incMonth = originalMonth;
    	int year = originalYear;
    	boolean found=true;
    	Date newDate = origDate;
    	int newOffset;
    	do{
    		if(incMonth==originalMonth&&year==originalYear-1){
    			found=false;
    			break;
    		}
    		if(incMonth==0){
    			incMonth=12;
    			year--;
    		}
    		newDate = new Date(year,incMonth,newDate.getDate(),newDate.getHours(),newDate.getMinutes(),newDate.getSeconds());
    		newOffset = gwtTimeZone.getOffset(newDate);
    	}while(newOffset==originalOffset);
    	if(!found)return instant;
    	
    	//add days until offset returns
    	long time = newDate.getTime();
    	do{
    		time+=DateTimeConstants.MILLIS_PER_DAY;
    		
    		newOffset = gwtTimeZone.getOffset(new Date(time));
    	}while(newOffset!=originalOffset);
    	
    	//subtract hours
    	do{
    		time-=DateTimeConstants.MILLIS_PER_HOUR;
    		
    		newOffset = gwtTimeZone.getOffset(new Date(time));
    	}while(newOffset==originalOffset);
    	
    	return time;
    }

    @SuppressWarnings("deprecation")
	public long nextTransition(long instant) {
    	Date date = new Date(instant);
    	//go forward in time by one month until the offset changes
    	//if we get back to this month, no transition occurs, and return instant
    	int originalOffset = gwtTimeZone.getOffset(date);
    	int originalYear = date.getYear();
    	int year = originalYear;
    	int month = date.getMonth();
    	int day = date.getDate();
    	int hour = date.getHours();
    	int minutes = date.getMinutes();
    	
    	int incMonth = month;
    	int newOffset;
    	boolean found=true;
    	Date newDate = date;
    	do{
    		if(incMonth==month&&year==originalYear+1){found=false; break;}
    		if(incMonth==11){
    			incMonth=0;
    			year++;
    		}else
    			incMonth++;
    		newDate = new Date(year,incMonth,day,hour,minutes,0);
    		newOffset = gwtTimeZone.getOffset(newDate);
    	}while(newOffset==originalOffset);
    	if(!found)return instant;
    	
    	//now go backwards by one day until the offset changes back again
    	long currentTime = newDate.getTime();
    	do{
    		currentTime-=DateTimeConstants.MILLIS_PER_DAY;
    		
    		newOffset = gwtTimeZone.getOffset(new Date(currentTime));
    	}while(newOffset!=originalOffset);
    	
    	//now go forwards by one hour until the offset changes forward
    	do{
    		currentTime+=DateTimeConstants.MILLIS_PER_HOUR;
    		newOffset = gwtTimeZone.getOffset(new Date(currentTime));
    	}while(newOffset==originalOffset);
    	
    	//go backwards by one minute until offset changes back
    	do{
    		currentTime-=DateTimeConstants.MILLIS_PER_MINUTE;
    		newOffset = gwtTimeZone.getOffset(new Date(currentTime));
    	}while(newOffset!=originalOffset);
    	
    	//at the limit of our accuracy, so return currentTime
    	return currentTime;
    }
    
	public boolean isFixed() {
		long currentTime = new Date().getTime();
		return nextTransition(currentTime)==currentTime;
    }
    
    public int getStandardOffset(long instant) {
        // TODO looks like in GWT standardOffset does not depend on instant
        return -1 * DateTimeConstants.MILLIS_PER_MINUTE* gwtTimeZone.getStandardOffset();
    }

    public int getOffset(long instant) {
    	return -1*DateTimeConstants.MILLIS_PER_MINUTE*gwtTimeZone.getOffset(new Date(instant));
    }

    public String getNameKey(long instant) {
        return gwtTimeZone.getShortName(new Date(instant));
    }

    public int hashCode() {
        return getID().hashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof GwtTimeZone) {
            // TODO not sure if this is correct (also for hashCode)...
            GwtTimeZone other = (GwtTimeZone) object;
            return getID().equals(other.getID());
        }
        return false;
    }
}