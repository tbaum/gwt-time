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
package org.joda.time.convert;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.chrono.BuddhistChronology;
import org.gwttime.time.chrono.ISOChronology;

/**
 * CalendarConverter converts a java util Calendar to an instant or partial.
 * The Calendar is converted to milliseconds and the chronology that best
 * matches the calendar.
 *
 * @author Stephen Colebourne
 * @since 1.0
 * @deprecated is not useable in GWT as java.util.Calendar is not available
 */
final class CalendarConverter extends AbstractConverter
        implements InstantConverter, PartialConverter {

    /**
     * Singleton instance.
     */
    static final CalendarConverter INSTANCE = new CalendarConverter();

    /**
     * Restricted constructor.
     */
    protected CalendarConverter() {
        super();
    }

    /**
     * Gets the chronology, which is the GJChronology if a GregorianCalendar is used,
     * BuddhistChronology if a BuddhistCalendar is used or ISOChronology otherwise.
     * The time zone specified is used in preference to that on the calendar.
     * 
     * @param object  the Calendar to convert, must not be null
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public Chronology getChronology(Object object, DateTimeZone zone) {
        if (object.getClass().getName().endsWith(".BuddhistCalendar")) {
            return BuddhistChronology.getInstance(zone);
        }else{
            return ISOChronology.getInstance(zone);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns Calendar.class.
     * 
     * @return Calendar.class
     */
    public Class<?> getSupportedType() {
        return CalendarConverter.class;
    }

}
