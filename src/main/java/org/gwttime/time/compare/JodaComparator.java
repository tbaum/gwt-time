package org.gwttime.time.compare;

import java.util.Comparator;

public abstract class JodaComparator<T> implements Comparator<T> {

	@Override
	public int compare(T arg0, T arg1) {
		if(arg0==arg1)return 0;
		else if(arg0==null){
			return 1;
		}else if(arg1==null) return -1;
		if(arg0.equals(arg1))return 0;
			
		long leftMillis = getMilliseconds(arg0);
		long rightMillis = getMilliseconds(arg1);
		
		long diff = leftMillis-rightMillis;
		if(diff<0)return -1;
		if(diff>0)return 1;
		return 0;
	}
	
	protected abstract long getMilliseconds(T element);

}
