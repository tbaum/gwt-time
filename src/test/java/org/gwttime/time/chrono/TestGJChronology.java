/*
 *  Copyright 2001-2005 Stephen Colebourne
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
package org.gwttime.time.chrono;


import java.util.Date;

import org.gwttime.time.DateTime;
import org.gwttime.time.DateTimeConstants;
import org.gwttime.time.DateTimeUtils;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.DurationField;
import org.gwttime.time.DurationFieldType;
import org.gwttime.time.Instant;
import org.gwttime.time.LocalDate;
import org.gwttime.time.chrono.GJChronology;
import org.gwttime.time.gwt.JodaGwtTestCase;

import static org.gwttime.time.gwt.TestConstants.*;
//import junit.framework.TestSuite;


import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * This class is a Junit unit test for GJChronology.
 *
 * @author Stephen Colebourne
 */
public class TestGJChronology extends JodaGwtTestCase {

    // Removed for GWT private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    // Removed for GWT private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    // Removed for GWT private static final DateTimeZone TOKYO = DateTimeZone.forID("Asia/Tokyo");

    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;

    private DateTimeZone originalDateTimeZone = null;
    private com.google.gwt.i18n.client.DateTimeFormat dtf = DateTimeFormat.getFormat("YYYY-MM-DD");

    /* Removed for GWT public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } */

    /* Removed for GWT public static TestSuite suite() {
        return new TestSuite(TestGJChronology.class);
    } */

    /* Removed for GWT public TestGJChronology(String name) {
        super(name);
    } */

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        /* //BEGIN GWT IGNORE
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        //END GWT IGNORE */
        DateTimeZone.setDefault(LONDON);
        /* //BEGIN GWT IGNORE
        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        //Locale.setDefault(Locale.UK);
        TimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo").toTimeZone());
        Locale.setDefault(Locale.JAPAN);
        //END GWT IGNORE */
    }

    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        /* //BEGIN GWT IGNORE
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        //END GWT IGNORE */
        originalDateTimeZone = null;
    }

    //-----------------------------------------------------------------------
    public void testFactoryUTC() {
        assertEquals(DateTimeZone.UTC, GJChronology.getInstanceUTC().getZone());
        assertSame(GJChronology.class, GJChronology.getInstanceUTC().getClass());
    }

    public void testFactory() {
        assertEquals(LONDON, GJChronology.getInstance().getZone());
        assertSame(GJChronology.class, GJChronology.getInstance().getClass());
    }

    public void testFactory_Zone() {
        assertEquals(TOKYO, GJChronology.getInstance(TOKYO).getZone());
        assertEquals(PARIS, GJChronology.getInstance(PARIS).getZone());
        assertEquals(LONDON, GJChronology.getInstance(null).getZone());
        assertSame(GJChronology.class, GJChronology.getInstance(TOKYO).getClass());
    }

    public void testFactory_Zone_long_int() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, 0L, 2);
        assertEquals(TOKYO, chrono.getZone());
        assertEquals(new Instant(0L), chrono.getGregorianCutover());
        assertEquals(2, chrono.getMinimumDaysInFirstWeek());
        assertSame(GJChronology.class, GJChronology.getInstance(TOKYO, 0L, 2).getClass());
        
        try {
            GJChronology.getInstance(TOKYO, 0L, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            GJChronology.getInstance(TOKYO, 0L, 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testFactory_Zone_RI() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L));
        assertEquals(TOKYO, chrono.getZone());
        assertEquals(new Instant(0L), chrono.getGregorianCutover());
        assertSame(GJChronology.class, GJChronology.getInstance(TOKYO, new Instant(0L)).getClass());
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null);
        assertEquals(TOKYO, chrono.getZone());
        assertEquals(cutover.toInstant(), chrono.getGregorianCutover());
    }

    public void testFactory_Zone_RI_int() {
        GJChronology chrono = GJChronology.getInstance(TOKYO, new Instant(0L), 2);
        assertEquals(TOKYO, chrono.getZone());
        assertEquals(new Instant(0L), chrono.getGregorianCutover());
        assertEquals(2, chrono.getMinimumDaysInFirstWeek());
        assertSame(GJChronology.class, GJChronology.getInstance(TOKYO, new Instant(0L), 2).getClass());
        
        DateTime cutover = new DateTime(1582, 10, 15, 0, 0, 0, 0, DateTimeZone.UTC);
        chrono = GJChronology.getInstance(TOKYO, null, 2);
        assertEquals(TOKYO, chrono.getZone());
        assertEquals(cutover.toInstant(), chrono.getGregorianCutover());
        assertEquals(2, chrono.getMinimumDaysInFirstWeek());
        
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            GJChronology.getInstance(TOKYO, new Instant(0L), 8);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testEquality() {
        assertSame(GJChronology.getInstance(TOKYO), GJChronology.getInstance(TOKYO));
        assertSame(GJChronology.getInstance(LONDON), GJChronology.getInstance(LONDON));
        assertSame(GJChronology.getInstance(PARIS), GJChronology.getInstance(PARIS));
        assertSame(GJChronology.getInstanceUTC(), GJChronology.getInstanceUTC());
        assertSame(GJChronology.getInstance(), GJChronology.getInstance(LONDON));
    }

    public void testWithUTC() {
        assertSame(GJChronology.getInstanceUTC(), GJChronology.getInstance(LONDON).withUTC());
        assertSame(GJChronology.getInstanceUTC(), GJChronology.getInstance(TOKYO).withUTC());
        assertSame(GJChronology.getInstanceUTC(), GJChronology.getInstanceUTC().withUTC());
        assertSame(GJChronology.getInstanceUTC(), GJChronology.getInstance().withUTC());
    }

    public void testWithZone() {
        assertSame(GJChronology.getInstance(TOKYO), GJChronology.getInstance(TOKYO).withZone(TOKYO));
        assertSame(GJChronology.getInstance(LONDON), GJChronology.getInstance(TOKYO).withZone(LONDON));
        assertSame(GJChronology.getInstance(PARIS), GJChronology.getInstance(TOKYO).withZone(PARIS));
        assertSame(GJChronology.getInstance(LONDON), GJChronology.getInstance(TOKYO).withZone(null));
        assertSame(GJChronology.getInstance(PARIS), GJChronology.getInstance().withZone(PARIS));
        assertSame(GJChronology.getInstance(PARIS), GJChronology.getInstanceUTC().withZone(PARIS));
    }

    public void testToString() {
        assertEquals("GJChronology[Europe/London]", GJChronology.getInstance(LONDON).toString());
        assertEquals("GJChronology[Asia/Tokyo]", GJChronology.getInstance(TOKYO).toString());
        assertEquals("GJChronology[Europe/London]", GJChronology.getInstance().toString());
        assertEquals("GJChronology[UTC]", GJChronology.getInstanceUTC().toString());
        assertEquals("GJChronology[UTC,cutover=1970-01-01]", GJChronology.getInstance(DateTimeZone.UTC, 0L, 4).toString());
        assertEquals("GJChronology[UTC,cutover=1970-01-01T00:00:00.001Z,mdfw=2]", GJChronology.getInstance(DateTimeZone.UTC, 1L, 2).toString());
    }

    //-----------------------------------------------------------------------
    public void testDurationFields() {
        assertEquals("eras", GJChronology.getInstance().eras().getName());
        assertEquals("centuries", GJChronology.getInstance().centuries().getName());
        assertEquals("years", GJChronology.getInstance().years().getName());
        assertEquals("weekyears", GJChronology.getInstance().weekyears().getName());
        assertEquals("months", GJChronology.getInstance().months().getName());
        assertEquals("weeks", GJChronology.getInstance().weeks().getName());
        assertEquals("halfdays", GJChronology.getInstance().halfdays().getName());
        assertEquals("days", GJChronology.getInstance().days().getName());
        assertEquals("hours", GJChronology.getInstance().hours().getName());
        assertEquals("minutes", GJChronology.getInstance().minutes().getName());
        assertEquals("seconds", GJChronology.getInstance().seconds().getName());
        assertEquals("millis", GJChronology.getInstance().millis().getName());
        
        assertEquals(false, GJChronology.getInstance().eras().isSupported());
        assertEquals(true, GJChronology.getInstance().centuries().isSupported());
        assertEquals(true, GJChronology.getInstance().years().isSupported());
        assertEquals(true, GJChronology.getInstance().weekyears().isSupported());
        assertEquals(true, GJChronology.getInstance().months().isSupported());
        assertEquals(true, GJChronology.getInstance().weeks().isSupported());
        assertEquals(true, GJChronology.getInstance().days().isSupported());
        assertEquals(true, GJChronology.getInstance().halfdays().isSupported());
        assertEquals(true, GJChronology.getInstance().hours().isSupported());
        assertEquals(true, GJChronology.getInstance().minutes().isSupported());
        assertEquals(true, GJChronology.getInstance().seconds().isSupported());
        assertEquals(true, GJChronology.getInstance().millis().isSupported());
        
        assertEquals(false, GJChronology.getInstance().centuries().isPrecise());
        assertEquals(false, GJChronology.getInstance().years().isPrecise());
        assertEquals(false, GJChronology.getInstance().weekyears().isPrecise());
        assertEquals(false, GJChronology.getInstance().months().isPrecise());
        assertEquals(false, GJChronology.getInstance().weeks().isPrecise());
        assertEquals(false, GJChronology.getInstance().days().isPrecise());
        assertEquals(false, GJChronology.getInstance().halfdays().isPrecise());
        assertEquals(true, GJChronology.getInstance().hours().isPrecise());
        assertEquals(true, GJChronology.getInstance().minutes().isPrecise());
        assertEquals(true, GJChronology.getInstance().seconds().isPrecise());
        assertEquals(true, GJChronology.getInstance().millis().isPrecise());
        
        assertEquals(false, GJChronology.getInstanceUTC().centuries().isPrecise());
        assertEquals(false, GJChronology.getInstanceUTC().years().isPrecise());
        assertEquals(false, GJChronology.getInstanceUTC().weekyears().isPrecise());
        assertEquals(false, GJChronology.getInstanceUTC().months().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().weeks().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().days().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().halfdays().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().hours().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().minutes().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().seconds().isPrecise());
        assertEquals(true, GJChronology.getInstanceUTC().millis().isPrecise());
        
        DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
        assertEquals(false, GJChronology.getInstance(gmt).centuries().isPrecise());
        assertEquals(false, GJChronology.getInstance(gmt).years().isPrecise());
        assertEquals(false, GJChronology.getInstance(gmt).weekyears().isPrecise());
        assertEquals(false, GJChronology.getInstance(gmt).months().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).weeks().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).days().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).halfdays().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).hours().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).minutes().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).seconds().isPrecise());
        assertEquals(true, GJChronology.getInstance(gmt).millis().isPrecise());
    }

    public void testDateFields() {
        assertEquals("era", GJChronology.getInstance().era().getName());
        assertEquals("centuryOfEra", GJChronology.getInstance().centuryOfEra().getName());
        assertEquals("yearOfCentury", GJChronology.getInstance().yearOfCentury().getName());
        assertEquals("yearOfEra", GJChronology.getInstance().yearOfEra().getName());
        assertEquals("year", GJChronology.getInstance().year().getName());
        assertEquals("monthOfYear", GJChronology.getInstance().monthOfYear().getName());
        assertEquals("weekyearOfCentury", GJChronology.getInstance().weekyearOfCentury().getName());
        assertEquals("weekyear", GJChronology.getInstance().weekyear().getName());
        assertEquals("weekOfWeekyear", GJChronology.getInstance().weekOfWeekyear().getName());
        assertEquals("dayOfYear", GJChronology.getInstance().dayOfYear().getName());
        assertEquals("dayOfMonth", GJChronology.getInstance().dayOfMonth().getName());
        assertEquals("dayOfWeek", GJChronology.getInstance().dayOfWeek().getName());
        
        assertEquals(true, GJChronology.getInstance().era().isSupported());
        assertEquals(true, GJChronology.getInstance().centuryOfEra().isSupported());
        assertEquals(true, GJChronology.getInstance().yearOfCentury().isSupported());
        assertEquals(true, GJChronology.getInstance().yearOfEra().isSupported());
        assertEquals(true, GJChronology.getInstance().year().isSupported());
        assertEquals(true, GJChronology.getInstance().monthOfYear().isSupported());
        assertEquals(true, GJChronology.getInstance().weekyearOfCentury().isSupported());
        assertEquals(true, GJChronology.getInstance().weekyear().isSupported());
        assertEquals(true, GJChronology.getInstance().weekOfWeekyear().isSupported());
        assertEquals(true, GJChronology.getInstance().dayOfYear().isSupported());
        assertEquals(true, GJChronology.getInstance().dayOfMonth().isSupported());
        assertEquals(true, GJChronology.getInstance().dayOfWeek().isSupported());
    }

    public void testTimeFields() {
        assertEquals("halfdayOfDay", GJChronology.getInstance().halfdayOfDay().getName());
        assertEquals("clockhourOfHalfday", GJChronology.getInstance().clockhourOfHalfday().getName());
        assertEquals("hourOfHalfday", GJChronology.getInstance().hourOfHalfday().getName());
        assertEquals("clockhourOfDay", GJChronology.getInstance().clockhourOfDay().getName());
        assertEquals("hourOfDay", GJChronology.getInstance().hourOfDay().getName());
        assertEquals("minuteOfDay", GJChronology.getInstance().minuteOfDay().getName());
        assertEquals("minuteOfHour", GJChronology.getInstance().minuteOfHour().getName());
        assertEquals("secondOfDay", GJChronology.getInstance().secondOfDay().getName());
        assertEquals("secondOfMinute", GJChronology.getInstance().secondOfMinute().getName());
        assertEquals("millisOfDay", GJChronology.getInstance().millisOfDay().getName());
        assertEquals("millisOfSecond", GJChronology.getInstance().millisOfSecond().getName());
        
        assertEquals(true, GJChronology.getInstance().halfdayOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().clockhourOfHalfday().isSupported());
        assertEquals(true, GJChronology.getInstance().hourOfHalfday().isSupported());
        assertEquals(true, GJChronology.getInstance().clockhourOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().hourOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().minuteOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().minuteOfHour().isSupported());
        assertEquals(true, GJChronology.getInstance().secondOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().secondOfMinute().isSupported());
        assertEquals(true, GJChronology.getInstance().millisOfDay().isSupported());
        assertEquals(true, GJChronology.getInstance().millisOfSecond().isSupported());
    }

    public void testIllegalDates() {
        try {
            new DateTime(1582, 10, 5, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }

        try {
            new DateTime(1582, 10, 14, 0, 0, 0, 0, GJChronology.getInstance(DateTimeZone.UTC));
            fail("Constructed illegal date");
        } catch (IllegalArgumentException e) { /* good */ }
    }

    public void testCutoverAddYears() {
    	Date startDate = dtf.parse("1582-01-01");
    	Date endDate = dtf.parse("1583-01-01");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-02-15");
        endDate = dtf.parse("1583-02-15");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-02-28");
        endDate = dtf.parse("1583-02-28");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-03-01");
        endDate = dtf.parse("1583-03-01");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-09-30");
        endDate = dtf.parse("1583-09-30");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-10-01");
        endDate = dtf.parse("1583-10-01");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-10-04");
        endDate = dtf.parse("1583-10-04");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-10-15");
        endDate = dtf.parse("1583-10-15");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        startDate = dtf.parse("1582-10-16");
        endDate = dtf.parse("1583-10-16");
        testAdd(startDate, DurationFieldType.years(), 1, endDate);
        

        // Leap years...
        startDate = dtf.parse("1580-01-01");
        endDate = dtf.parse("1584-01-01");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
        startDate = dtf.parse("1580-02-29");
        endDate = dtf.parse("1584-02-29");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
        startDate = dtf.parse("1580-10-01");
        endDate = dtf.parse("1584-10-01");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
        startDate = dtf.parse("1580-10-10");
        endDate = dtf.parse("1584-10-10");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
        startDate = dtf.parse("1580-10-15");
        endDate = dtf.parse("1584-10-15");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
        startDate = dtf.parse("1580-12-31");
        endDate = dtf.parse("1584-12-31");
        testAdd(startDate, DurationFieldType.years(), 4, endDate);
     
    }

    public void testCutoverAddWeekyears() {
    	Date startDate = dtf.parse("1582-01-01");
    	Date endDate = dtf.parse("1583-01-01");
        testAdd(startDate, DurationFieldType.weekyears(), 1, endDate);
        startDate = dtf.parse("1582-09-23");
        endDate = dtf.parse("1583-09-23");
        testAdd(startDate, DurationFieldType.weekyears(), 1, endDate);
        startDate = dtf.parse("1582-11-11");
        endDate = dtf.parse("1583-11-11");
        testAdd(startDate, DurationFieldType.weekyears(), 1, endDate);

        // This test fails, but I'm not sure if its worth fixing. The date
        // falls after the cutover, but in the cutover year. The add operation
        // is performed completely within the gregorian calendar, with no
        // crossing of the cutover. As a result, no special correction is
        // applied. Since the full gregorian year of 1582 has a different week
        // numbers than the full julian year of 1582, the week number is off by
        // one after the addition.
        //
        //testAdd("1582-W42-1", DurationFieldType.weekyears(), 1, "1583-W42-1");

        // Leap years...
        startDate= dtf.parse("1580-01-01");
        endDate = dtf.parse("1583-01-01");
        testAdd(startDate, DurationFieldType.weekyears(), 4, endDate);
        startDate= dtf.parse("1580-07-28");
        endDate = dtf.parse("1583-08-04");
        testAdd(startDate, DurationFieldType.weekyears(), 4, endDate);
        startDate= dtf.parse("1580-12-15");
        endDate = dtf.parse("1583-12-23");
        testAdd(startDate, DurationFieldType.weekyears(), 4, endDate);
    }

    public void testCutoverAddMonths() {
    	Date startDate = dtf.parse("1582-01-01");
    	Date endDate = dtf.parse("1582-02-01");
    	testAdd(startDate, DurationFieldType.months(), 1, endDate);
        startDate = dtf.parse("1582-01-01");
        endDate = dtf.parse("1582-07-01");
        testAdd(startDate, DurationFieldType.months(), 6, endDate);
        startDate = dtf.parse("1582-01-01");
        endDate = dtf.parse("1583-01-01");
        testAdd(startDate, DurationFieldType.months(), 12, endDate);
        startDate = dtf.parse("1582-11-15");
        endDate = dtf.parse("1582-12-15");
        testAdd(startDate, DurationFieldType.months(), 1, endDate);

        startDate = dtf.parse("1582-09-04");
        endDate = dtf.parse("1582-11-04");
        testAdd(startDate, DurationFieldType.months(), 2, endDate);
        startDate = dtf.parse("1582-09-05");
        endDate = dtf.parse("1582-11-05");
        testAdd(startDate, DurationFieldType.months(), 2, endDate);
        startDate = dtf.parse("1582-09-10");
        endDate = dtf.parse("1582-11-10");
        testAdd(startDate, DurationFieldType.months(), 2, endDate);
        startDate = dtf.parse("1582-09-15");
        endDate = dtf.parse("1582-11-15");
        testAdd(startDate, DurationFieldType.months(), 2, endDate);


        // Leap years...
        startDate = dtf.parse("1580-01-01");
        endDate = dtf.parse("1584-01-01");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
        startDate = dtf.parse("1580-02-29");
        endDate = dtf.parse("1584-02-29");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
        startDate = dtf.parse("1580-10-01");
        endDate = dtf.parse("1584-10-01");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
        startDate = dtf.parse("1580-10-10");
        endDate = dtf.parse("1584-10-10");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
        startDate = dtf.parse("1580-10-15");
        endDate = dtf.parse("1584-10-15");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
        startDate = dtf.parse("1580-12-31");
        endDate = dtf.parse("1584-12-31");
        testAdd(startDate, DurationFieldType.months(), 48, endDate);
    }

    public void testCutoverAddWeeks() {
    	Date startDate = dtf.parse("1582-01-01");
    	Date endDate = dtf.parse("1582-01-08");
        testAdd(startDate, DurationFieldType.weeks(), 1, endDate);
    	startDate = dtf.parse("1583-01-01");
    	endDate = dtf.parse("1583-01-08");
        testAdd(startDate, DurationFieldType.weeks(), 1, endDate);

        // Weeks are precise, and so cutover is not ignored.
        startDate = dtf.parse("1582-10-01");
        endDate = dtf.parse("1582-10-25");
        testAdd(startDate, DurationFieldType.weeks(), 2, endDate);
        startDate = dtf.parse("1582-01-01");
        endDate = dtf.parse("1583-01-01");
        testAdd(startDate, DurationFieldType.weeks(), 51, endDate);
    }

    public void testCutoverAddDays() {
    	Date startDate = dtf.parse("1582-10-03");
    	Date endDate = dtf.parse("1582-10-04");
        testAdd(startDate, DurationFieldType.days(), 1, endDate);
        startDate = dtf.parse("1582-10-14");
        endDate = dtf.parse("1582-10-15");
        testAdd(startDate, DurationFieldType.days(), 1, endDate);
        startDate = dtf.parse("1582-10-15");
        endDate = dtf.parse("1582-10-16");
        testAdd(startDate, DurationFieldType.days(), 1, endDate);

        startDate = dtf.parse("1582-09-30");
        endDate = dtf.parse("1582-10-10");
        testAdd(startDate, DurationFieldType.days(), 10, endDate);
        startDate = dtf.parse("1582-10-14");
        endDate = dtf.parse("1582-10-24");
        testAdd(startDate, DurationFieldType.days(), 10, endDate);
        startDate = dtf.parse("1582-10-15");
        endDate = dtf.parse("1582-10-25");
        testAdd(startDate, DurationFieldType.days(), 10, endDate);
    }

    public void testYearEndAddDays() {
    	Date startDate = dtf.parse("1582-11-05");
    	Date endDate = dtf.parse("1582-12-03");
        testAdd(startDate, DurationFieldType.days(), 28, endDate);
        startDate = dtf.parse("1582-12-05");
        endDate = dtf.parse("1583-01-02");
        testAdd(startDate, DurationFieldType.days(), 28, endDate);
        
        startDate = dtf.parse("2005-11-05");
        endDate = dtf.parse("2005-12-03");
        testAdd(startDate, DurationFieldType.days(), 28, endDate);
        startDate = dtf.parse("2005-12-05");
        endDate = dtf.parse("2006-01-02");
        testAdd(startDate, DurationFieldType.days(), 28, endDate);
    }

    private void testAdd(Date date, DurationFieldType type, int amt, Date end) {
    	
        DateTime dtStart = new DateTime(date, GJChronology.getInstance(DateTimeZone.UTC));
        DateTime dtEnd = new DateTime(date, GJChronology.getInstance(DateTimeZone.UTC));
        assertEquals(dtEnd, dtStart.withFieldAdded(type, amt));
        assertEquals(dtStart, dtEnd.withFieldAdded(type, -amt));

        DurationField field = type.getField(GJChronology.getInstance(DateTimeZone.UTC));
        int diff = field.getDifference(dtEnd.getMillis(), dtStart.getMillis());
        assertEquals(amt, diff);
        
        if (type == DurationFieldType.years() ||
            type == DurationFieldType.months() ||
            type == DurationFieldType.days()) {
            LocalDate ymdStart = new LocalDate(date, GJChronology.getInstance(DateTimeZone.UTC));
            LocalDate ymdEnd = new LocalDate(end, GJChronology.getInstance(DateTimeZone.UTC));
            assertEquals(ymdEnd, ymdStart.withFieldAdded(type, amt));
            assertEquals(ymdStart, ymdEnd.withFieldAdded(type, -amt));
        }
    }


    //GWT: result depends on the locale
    public void testPartialGetAsText() {
        GJChronology chrono = GJChronology.getInstance(TOKYO);
        com.google.gwt.i18n.client.DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
        assertEquals("January", new LocalDate(dtf.parse("2005-01-01"), chrono).monthOfYear().getAsText());
        assertEquals("Jan", new LocalDate(dtf.parse("2005-01-01"), chrono).monthOfYear().getAsShortText());
    }
}
