/*
 *  Copyright 2001-2009 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.gwttime.time.tz;

import org.gwttime.time.DateTimeZone;

import com.google.gwt.i18n.client.LocaleInfo;

import java.util.Date;

/**
 * The default name provider acquires localized names from
 * {@link DateFormatSymbols java.text.DateFormatSymbols}.
 * <p>
 * DefaultNameProvider is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class DefaultNameProvider implements NameProvider {

    public DefaultNameProvider() {
    }

    public String getShortName(LocaleInfo locale, String id, String nameKey) {
    	return nameKey;
    }
    
    public String getName(LocaleInfo locale, String id, String nameKey) {
    	if(locale==null)
    		locale = LocaleInfo.getCurrentLocale();
    	if(id==null||id.length()<=0)
    		return nameKey;
    	DateTimeZone zone = GwtZoneInfoProvider.singleton().getZone(id);
    	if(!(zone instanceof GwtTimeZone)){
    		String localeName = locale.getLocaleName();
    		if("default".equalsIgnoreCase(localeName))
    			return nameKey;

    		return localeName;
    	}else{
    		return ((GwtTimeZone)zone).getGwtTimeZone().getLongName(new Date());
    	}
    }
}
