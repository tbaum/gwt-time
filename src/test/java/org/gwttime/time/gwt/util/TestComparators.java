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
package org.gwttime.time.gwt.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.gwttime.time.DateTime;
import org.gwttime.time.DateTimeFieldType;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.ReadableInstant;
import org.gwttime.time.format.DateTimeFormat;
import org.gwttime.time.format.DateTimeFormatter;
import org.gwttime.time.gwt.JodaGwtTestCase;
import org.gwttime.time.util.Comparators;


/**
 * This class is a Junit unit test for the
 * org.joda.time.DateTimeComparator class.
 *
 * @author Guy Allard
 */
public class TestComparators extends JodaGwtTestCase {

    /**
     * A reference to a DateTime object.
     */
    DateTime aDateTime = null;
    /**
     * A reference to a DateTime object.
     */
    DateTime bDateTime = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for millis of seconds.
     */
    Comparator<DateTime> millisCompare = Comparators.getRoundingComparator(null,DateTimeFieldType.secondOfMinute());
    Comparator<Date> millisDateCompare = Comparators.getDateComparator(null,DateTimeFieldType.secondOfMinute());
    Comparator<Long> millisLongCompare = Comparators.getLongComparator(null,DateTimeFieldType.secondOfMinute());
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for seconds.
     */
    Comparator<DateTime> secondCompare = Comparators.getRoundingComparator(DateTimeFieldType.secondOfMinute(),DateTimeFieldType.minuteOfHour());
    Comparator<Date> secondDateCompare = Comparators.getDateComparator(DateTimeFieldType.secondOfMinute(),DateTimeFieldType.minuteOfHour());
    Comparator<Long> secondLongCompare = Comparators.getLongComparator(DateTimeFieldType.secondOfMinute(),DateTimeFieldType.minuteOfHour());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for minutes.
     */
    Comparator<DateTime> minuteCompare = Comparators.getRoundingComparator(DateTimeFieldType.minuteOfHour(),DateTimeFieldType.hourOfDay());
    Comparator<Date> minuteDateCompare = Comparators.getDateComparator(DateTimeFieldType.minuteOfHour(),DateTimeFieldType.hourOfDay());
    Comparator<Long> minuteLongCompare = Comparators.getLongComparator(DateTimeFieldType.minuteOfHour(),DateTimeFieldType.hourOfDay());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for hours.
     */
    Comparator<DateTime> hourCompare = Comparators.getRoundingComparator(DateTimeFieldType.hourOfDay(),null);
    Comparator<Date> hourDateCompare = Comparators.getDateComparator(DateTimeFieldType.hourOfDay(),DateTimeFieldType.dayOfYear());
    Comparator<Long> hourLongCompare = Comparators.getLongComparator(DateTimeFieldType.hourOfDay(),DateTimeFieldType.dayOfYear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the week.
     */
    Comparator<DateTime> dayOfWeekCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfWeek(),DateTimeFieldType.weekOfWeekyear());
    Comparator<Date> dayOfWeekDateCompare = Comparators.getDateComparator(DateTimeFieldType.dayOfWeek(),DateTimeFieldType.weekOfWeekyear());
    Comparator<Long> dayOfWeekLongCompare = Comparators.getLongComparator(DateTimeFieldType.dayOfWeek(),DateTimeFieldType.weekOfWeekyear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the month.
     */
    Comparator<DateTime> dayOfMonthCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfMonth(),DateTimeFieldType.monthOfYear());
    Comparator<Date> dayOfMonthDateCompare = Comparators.getDateComparator(DateTimeFieldType.dayOfMonth(),DateTimeFieldType.monthOfYear());
    Comparator<Long> dayOfMonthLongCompare = Comparators.getLongComparator(DateTimeFieldType.dayOfMonth(),DateTimeFieldType.monthOfYear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the year.
     */
    Comparator<DateTime> dayOfYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfYear(),DateTimeFieldType.year());
    Comparator<Date> dayOfYearDateCompare = Comparators.getDateComparator(DateTimeFieldType.dayOfYear(),DateTimeFieldType.year());
    Comparator<Long> dayOfYearLongCompare = Comparators.getLongComparator(DateTimeFieldType.dayOfYear(),DateTimeFieldType.year());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for week of the weekyear.
     */
    Comparator<DateTime> weekOfWeekYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.weekOfWeekyear(),DateTimeFieldType.weekyear());
    Comparator<Date> weekOfWeekYearDateCompare = Comparators.getDateComparator(DateTimeFieldType.weekOfWeekyear(),DateTimeFieldType.weekyear());
    Comparator<Long> weekOfWeekYearLongCompare = Comparators.getLongComparator(DateTimeFieldType.weekOfWeekyear(),DateTimeFieldType.weekyear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for year given a week of the year.
     */
    Comparator<DateTime> weekYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.weekyear());
    Comparator<Date> weekYearDateCompare = Comparators.getDateComparator(DateTimeFieldType.weekyear());
    Comparator<Long> weekYearLongCompare = Comparators.getLongComparator(DateTimeFieldType.weekyear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for months.
     */
    Comparator<DateTime> monthCompare = Comparators.getRoundingComparator(DateTimeFieldType.monthOfYear(),DateTimeFieldType.year());
    Comparator<Date> monthDateCompare = Comparators.getDateComparator(DateTimeFieldType.monthOfYear(),DateTimeFieldType.year());
    Comparator<Long> monthLongCompare = Comparators.getLongComparator(DateTimeFieldType.monthOfYear(),DateTimeFieldType.year());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for year.
     */
    Comparator<DateTime> yearCompare = Comparators.getRoundingComparator(DateTimeFieldType.year());
    Comparator<Date> yearDateCompare = Comparators.getDateComparator(DateTimeFieldType.year());
    Comparator<Long> yearLongCompare = Comparators.getLongComparator(DateTimeFieldType.year());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for the date portion of an
     * object.
     */
    Comparator<DateTime> dateCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfYear());
    Comparator<Date> dateDateCompare = Comparators.getDateComparator(DateTimeFieldType.dayOfYear());
    Comparator<Long> dateLongCompare = Comparators.getLongComparator(DateTimeFieldType.dayOfYear());
    
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for the time portion of an
     * object.
     */
    Comparator<DateTime> timeCompare = Comparators.getRoundingComparator(null,DateTimeFieldType.dayOfYear());
    Comparator<Date> timeDateCompare = Comparators.getDateComparator(null,DateTimeFieldType.dayOfYear());
    Comparator<Long> timeLongCompare = Comparators.getLongComparator(null,DateTimeFieldType.dayOfYear());
    
    DateTimeFormatter dtf; 
    /**
     * Junit <code>setUp()</code> method.
     */
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        dtf= DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    }

    /**
     * Junit <code>tearDown()</code> method.
     */
    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        // super.tearDown();
        aDateTime = null;
        bDateTime = null;
        //
//        cMillis = null;
//        cSecond = null;
//        cMinute = null;
//        cHour = null;
//        cDayOfWeek = null;
//        cDayOfMonth = null;
//        cDayOfYear = null;
//        cWeekOfWeekyear = null;
//        cWeekyear = null;
//        cMonth = null;
//        cYear = null;
//        cDate = null;
//        cTime = null;
    }

    //-----------------------------------------------------------------------
    /* //BEGIN GWT IGNORE
    public void testClass() {
        assertEquals(true, Modifier.isPublic(DateTimeComparator.class.getModifiers()));
        assertEquals(false, Modifier.isFinal(DateTimeComparator.class.getModifiers()));
        assertEquals(1, DateTimeComparator.class.getDeclaredConstructors().length);
        assertEquals(true, Modifier.isProtected(DateTimeComparator.class.getDeclaredConstructors()[0].getModifiers()));
    }
    //END GWT IGNORE */
    
    //-----------------------------------------------------------------------
//    public void testStaticGetInstance() {
//        DateTimeComparator c = DateTimeComparator.getInstance();
//        assertEquals(null, c.getLowerLimit());
//        assertEquals(null, c.getUpperLimit());
//        assertEquals("DateTimeComparator[]", c.toString());
//    }        
//    public void testStaticGetDateOnlyInstance() {
//        DateTimeComparator c = DateTimeComparator.getDateOnlyInstance();
//        assertEquals(DateTimeFieldType.dayOfYear(), c.getLowerLimit());
//        assertEquals(null, c.getUpperLimit());
//        assertEquals("DateTimeComparator[dayOfYear-]", c.toString());
//        
//        assertSame(DateTimeComparator.getDateOnlyInstance(), DateTimeComparator.getDateOnlyInstance());
//    }
//    public void testStaticGetTimeOnlyInstance() {
//        DateTimeComparator c = DateTimeComparator.getTimeOnlyInstance();
//        assertEquals(null, c.getLowerLimit());
//        assertEquals(DateTimeFieldType.dayOfYear(), c.getUpperLimit());
//        assertEquals("DateTimeComparator[-dayOfYear]", c.toString());
//        
//        assertSame(DateTimeComparator.getTimeOnlyInstance(), DateTimeComparator.getTimeOnlyInstance());
//    }
//    public void testStaticGetInstanceLower() {
//        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay());
//        assertEquals(DateTimeFieldType.hourOfDay(), c.getLowerLimit());
//        assertEquals(null, c.getUpperLimit());
//        assertEquals("DateTimeComparator[hourOfDay-]", c.toString());
//        
//        c = DateTimeComparator.getInstance(null);
//        assertSame(DateTimeComparator.getInstance(), c);
//    }
//    public void testStaticGetInstanceLowerUpper() {
//        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
//        assertEquals(DateTimeFieldType.hourOfDay(), c.getLowerLimit());
//        assertEquals(DateTimeFieldType.dayOfYear(), c.getUpperLimit());
//        assertEquals("DateTimeComparator[hourOfDay-dayOfYear]", c.toString());
//        
//        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
//        assertEquals(DateTimeFieldType.hourOfDay(), c.getLowerLimit());
//        assertEquals(DateTimeFieldType.hourOfDay(), c.getUpperLimit());
//        assertEquals("DateTimeComparator[hourOfDay]", c.toString());
//        
//        c = DateTimeComparator.getInstance(null, null);
//        assertSame(DateTimeComparator.getInstance(), c);
//        
//        c = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), null);
//        assertSame(DateTimeComparator.getDateOnlyInstance(), c);
//        
//        c = DateTimeComparator.getInstance(null, DateTimeFieldType.dayOfYear());
//        assertSame(DateTimeComparator.getTimeOnlyInstance(), c);
//    }
//    
//    //-----------------------------------------------------------------------
//    public void testEqualsHashCode() {
//        DateTimeComparator c1 = DateTimeComparator.getInstance();
//        assertEquals(true, c1.equals(c1));
//        assertEquals(false, c1.equals(null));
//        assertEquals(true, c1.hashCode() == c1.hashCode());
//        
//        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
//        assertEquals(true, c2.equals(c2));
//        assertEquals(false, c2.equals(c1));
//        assertEquals(false, c1.equals(c2));
//        assertEquals(false, c2.equals(null));
//        assertEquals(false, c1.hashCode() == c2.hashCode());
//        
//        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
//        assertEquals(true, c3.equals(c3));
//        assertEquals(false, c3.equals(c1));
//        assertEquals(true, c3.equals(c2));
//        assertEquals(false, c1.equals(c3));
//        assertEquals(true, c2.equals(c3));
//        assertEquals(false, c1.hashCode() == c3.hashCode());
//        assertEquals(true, c2.hashCode() == c3.hashCode());
//        
//        DateTimeComparator c4 = DateTimeComparator.getDateOnlyInstance();
//        assertEquals(false, c4.hashCode() == c3.hashCode());
//    }
    
    //-----------------------------------------------------------------------
    /* //BEGIN GWT IGNORE
    public void testSerialization1() throws Exception {
        DateTimeField f = ISO.dayOfYear();
        f.toString();
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c);
        byte[] bytes = baos.toByteArray();
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeComparator result = (DateTimeComparator) ois.readObject();
        ois.close();
        
        assertEquals(c, result);
    }
    //END GWT IGNORE */

    //-----------------------------------------------------------------------
    /* //BEGIN GWT IGNORE
    public void testSerialization2() throws Exception {
        DateTimeComparator c = DateTimeComparator.getInstance();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c);
        byte[] bytes = baos.toByteArray();
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeComparator result = (DateTimeComparator) ois.readObject();
        ois.close();
        
        assertSame(c, result);
    }
    //END GWT IGNORE */

    //-----------------------------------------------------------------------
    /**
     * Test all basic comparator operation with DateTime objects.
     */
    public void testBasicComps1() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        
        assertEquals( "getMillis", aDateTime.getMillis(),
            bDateTime.getMillis() );
        assertEquals("MILLIS",0,millisCompare.compare(aDateTime,bDateTime));      
        assertEquals( "SECOND", 0, secondCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MINUTE", 0, minuteCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HOUR", 0, hourCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOW", 0, dayOfWeekCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOM", 0, dayOfMonthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOY", 0, dayOfYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WOW", 0, weekOfWeekYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WY", 0, weekYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MONTH", 0, monthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEAR", 0, yearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DATE", 0, dateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "TIME", 0, timeCompare.compare( aDateTime, bDateTime ) );
    }   // end of testBasicComps


    /**
     * Test all basic comparator operation with ReadableInstant objects.
     */
    public void testBasicComps2() {
    	/*
    	 * Note: This test may be useless after the conversion to using Comparators.getRoundingComparator
    	 * was implemented. -sf-
    	 */
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        assertEquals( "getMillis", aDateTime.getMillis(),
            bDateTime.getMillis() );
        
        Comparator<ReadableInstant> millisCompare =Comparators.getRoundingComparator(null, DateTimeFieldType.secondOfMinute());
        assertEquals( "MILLIS", 0, millisCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> secondsCompare = Comparators.getRoundingComparator(DateTimeFieldType.secondOfMinute(), DateTimeFieldType.minuteOfHour());
        assertEquals( "SECOND", 0, secondsCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> minutesCompare = Comparators.getRoundingComparator(DateTimeFieldType.minuteOfHour(), DateTimeFieldType.hourOfDay());
        assertEquals( "MINUTE", 0, minutesCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> hourCompare = Comparators.getRoundingComparator(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        assertEquals( "HOUR", 0, hourCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> dayOfWeekCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfWeek(), DateTimeFieldType.weekOfWeekyear());
        assertEquals( "DOW", 0, dayOfWeekCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> dayOfMonthCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear());
        assertEquals( "DOM", 0, dayOfMonthCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> dayOfYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
        assertEquals( "DOY", 0, dayOfYearCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> weekOfWeekYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.weekOfWeekyear(), DateTimeFieldType.weekyear());
        assertEquals( "WOW", 0, weekOfWeekYearCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> weekYearCompare = Comparators.getRoundingComparator(DateTimeFieldType.weekyear());
        assertEquals( "WY", 0, weekYearCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> monthCompare = Comparators.getRoundingComparator(DateTimeFieldType.monthOfYear(), DateTimeFieldType.year());
        assertEquals( "MONTH", 0, monthCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> yearCompare = Comparators.getRoundingComparator(DateTimeFieldType.year());
        assertEquals( "YEAR", 0, yearCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> dateCompare = Comparators.getRoundingComparator(DateTimeFieldType.dayOfYear());
        assertEquals( "DATE", 0, dateCompare.compare( aDateTime, bDateTime ) );
        Comparator<ReadableInstant> timeCompare = Comparators.getRoundingComparator(null,DateTimeFieldType.dayOfYear());
        assertEquals( "TIME", 0, timeCompare.compare( aDateTime, bDateTime ) );
    }   // end of testBasicComps

    /**
     * Test all basic comparator operation with java Date objects.
     */
    public void testBasicComps3() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        assertEquals( "MILLIS", 0, millisDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "SECOND", 0, secondDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MINUTE", 0, minuteDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HOUR", 0, hourDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOW", 0, dayOfWeekDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOM", 0, dayOfMonthDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOY", 0, dayOfYearDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WOW", 0, weekOfWeekYearDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WY", 0, weekYearDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MONTH", 0, monthDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEAR", 0, yearDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DATE", 0, dateDateCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "TIME", 0, timeDateCompare.compare( aDateTime, bDateTime ) );
    }   // end of testBasicComps

    /**
     * Test all basic comparator operation with Long objects.
     */
    public void testBasicComps4() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        assertEquals( "MILLIS", 0, millisLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "SECOND", 0, secondLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MINUTE", 0, minuteLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HOUR", 0, hourLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOW", 0, dayOfWeekLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOM", 0, dayOfMonthLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOY", 0, dayOfYearLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WOW", 0, weekOfWeekYearLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WY", 0, weekYearLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MONTH", 0, monthLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEAR", 0, yearLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DATE", 0, dateLongCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "TIME", 0, timeLongCompare.compare( aDateTime, bDateTime ) );
    }   // end of testBasicComps

//    /**
//     * Test all basic comparator operation with Calendar objects.
//     */
//    public void testBasicComps5() {
//        Calendar aDateTime
//            = Calendar.getInstance();   // right now
//        Calendar bDateTime = aDateTime;
//        assertEquals( "MILLIS", 0, millisCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "SECOND", 0, secondCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "MINUTE", 0, minuteCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "HOUR", 0, hourCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "DOW", 0, dayOfWeekCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "DOM", 0, dayOfMonthCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "DOY", 0, dayOfYearCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "WOW", 0, weekOfWeekYearCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "WY", 0, weekYearCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "MONTH", 0, monthCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "YEAR", 0, yearCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "DATE", 0, dateCompare.compare( aDateTime, bDateTime ) );
//        assertEquals( "TIME", 0, timeCompare.compare( aDateTime, bDateTime ) );
//    }   // end of testBasicComps


    /**
     * Test unequal comparisons with millis of second comparators.
     */
    public void testMillis() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis() + 1, DateTimeZone.UTC );
        Comparator<DateTime> millisCompare = Comparators.getRoundingComparator(null, DateTimeFieldType.secondOfMinute());
        assertEquals( "MillisM1", -1, millisCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MillisP1", 1, millisCompare.compare( bDateTime, aDateTime ) );
    }   // end of testMillis

    /**
     * Test unequal comparisons with second comparators.
     */
    public void testSecond() {
        aDateTime = getADate( ("1969-12-31T23:59:58" ));
        bDateTime = getADate( ("1969-12-31T23:59:59" ));
        assertEquals( "SecondM1a", -1, secondCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "SecondP1a", 1, secondCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1970-01-01T00:00:00" ));
        bDateTime = getADate( ("1970-01-01T00:00:01" ));
        assertEquals( "SecondM1b", -1, secondCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "SecondP1b", 1, secondCompare.compare( bDateTime, aDateTime ) );
    }   // end of testSecond

    /**
     * Test unequal comparisons with minute comparators.
     */
    public void testMinute() {
    	
        aDateTime = getADate( ("1969-12-31T23:58:00" ));
        bDateTime = getADate( ("1969-12-31T23:59:00" ));
        assertEquals( "MinuteM1a", -1, minuteCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MinuteP1a", 1, minuteCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1970-01-01T00:00:00" ));
        bDateTime = getADate( ("1970-01-01T00:01:00" ));
        assertEquals( "MinuteM1b", -1, minuteCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MinuteP1b", 1, minuteCompare.compare( bDateTime, aDateTime ) );
    }   // end of testMinute

    /**
     * Test unequal comparisons with hour comparators.
     */
    public void testHour() {
        aDateTime = getADate( ("1969-12-31T22:00:00") );
        bDateTime = getADate( ("1969-12-31T23:00:00") );
        assertEquals( "HourM1a", -1, hourCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HourP1a", 1, hourCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1970-01-01T00:00:00") );
        bDateTime = getADate( ("1970-01-01T01:00:00" ));
        assertEquals( "HourM1b", -1, hourCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HourP1b", 1, hourCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1969-12-31T23:59:59" ));
        bDateTime = getADate( ("1970-01-01T00:00:00" ));
        assertEquals( "HourP1c", -1, hourCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "HourM1c", 1, hourCompare.compare( bDateTime, aDateTime ) );
    }   // end of testHour

    /**
     * Test unequal comparisons with day of week comparators.
     */
    public void testDOW() {
        /*
         * Dates chosen when I wrote the code, so I know what day of
         * the week it is.
         */
        aDateTime = getADate( ("2002-04-12T00:00:00" ));
        bDateTime = getADate( ("2002-04-13T00:00:00" ));
        assertEquals( "DOWM1a", -1, dayOfWeekCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOWP1a", 1, dayOfWeekCompare.compare( bDateTime, aDateTime ) );
    }   // end of testDOW

    /**
     * Test unequal comparisons with day of month comparators.
     */
    public void testDOM() {
        aDateTime = getADate( ("2002-04-12T00:00:00" ));
        bDateTime = getADate( ("2002-04-13T00:00:00" ));
        assertEquals( "DOMM1a", -1, dayOfMonthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOMP1a", 1, dayOfMonthCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("2000-12-01T00:00:00" ));
        bDateTime = getADate( ("1814-04-30T00:00:00" ));
        assertEquals( "DOMM1b", -1, dayOfMonthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOMP1b", 1, dayOfMonthCompare.compare( bDateTime, aDateTime ) );
    }   // end of testDOM

    /**
     * Test unequal comparisons with day of year comparators.
     */
    public void testDOY() {
        aDateTime = getADate( ("2002-04-12T00:00:00" ));
        bDateTime = getADate( ("2002-04-13T00:00:00" ));
        assertEquals( "DOYM1a", -1, dayOfYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOYP1a", 1, dayOfYearCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("2000-02-29T00:00:00" ));
        bDateTime = getADate( ("1814-11-30T00:00:00" ));
        assertEquals( "DOYM1b", -1, dayOfYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "DOYP1b", 1, dayOfYearCompare.compare( bDateTime, aDateTime ) );
    }   // end of testDOY

    /**
     * Test unequal comparisons with week of weekyear comparators.
     */
    public void testWOW() {
        // 1st week of year contains Jan 04.
        aDateTime = getADate( ("2000-01-04T00:00:00" ));
        bDateTime = getADate( ("2000-01-11T00:00:00" ));
        assertEquals( "WOWM1a", -1,
            weekOfWeekYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WOWP1a", 1,
            weekOfWeekYearCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("2000-01-04T00:00:00" ));
        bDateTime = getADate( ("1999-12-31T00:00:00" ));
        assertEquals( "WOWM1b", -1,
            weekOfWeekYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "WOWP1b", 1,
            weekOfWeekYearCompare.compare( bDateTime, aDateTime ) );
    }   // end of testMillis

    /**
     * Test unequal comparisons with year given the week comparators.
     */
    public void testWOYY() {
        // How do I test the end conditions of this?
        // Don't understand ......
        aDateTime = getADate( ("1998-12-31T23:59:59" ));
        bDateTime = getADate( ("1999-01-01T00:00:00" ));
        assertEquals( "YOYYZ", 0, weekYearCompare.compare( aDateTime, bDateTime ) );
        bDateTime = getADate( ("1999-01-04T00:00:00" ));
        assertEquals( "YOYYM1", -1, weekYearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YOYYP1", 1, weekYearCompare.compare( bDateTime, aDateTime ) );
    }   // end of testWOYY

    /**
     * Test unequal comparisons with month comparators.
     */
    public void testMonth() {
        aDateTime = getADate( ("2002-04-30T00:00:00" ));
        bDateTime = getADate( ("2002-05-01T00:00:00" ));
        assertEquals( "MONTHM1a", -1, monthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MONTHP1a", 1, monthCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1900-01-01T00:00:00" ));
        bDateTime = getADate( ("1899-12-31T00:00:00" ));
        assertEquals( "MONTHM1b", -1, monthCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "MONTHP1b", 1, monthCompare.compare( bDateTime, aDateTime ) );
    }   // end of testMonth

    /**
     * Test unequal comparisons with year comparators.
     */
    public void testYear() {
        aDateTime = getADate(("2000-01-01T00:00:00" ));
        bDateTime = getADate( ("2001-01-01T00:00:00" ));
        assertEquals( "YEARM1a", -1, yearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEARP1a", 1, yearCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1968-12-31T23:59:59" ));
        bDateTime = getADate( ("1970-01-01T00:00:00" ));
        assertEquals( "YEARM1b", -1, yearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEARP1b", 1, yearCompare.compare( bDateTime, aDateTime ) );
        aDateTime = getADate( ("1969-12-31T23:59:59" ));
        bDateTime = getADate( ("1970-01-01T00:00:00" ));
        assertEquals( "YEARM1c", -1, yearCompare.compare( aDateTime, bDateTime ) );
        assertEquals( "YEARP1c", 1, yearCompare.compare( bDateTime, aDateTime ) );
    }   // end of testYear

    /*
     * 'List' processing tests follow.
     */

     /**
      * Test sorting with full default comparator.
      */
     public void testListBasic() {
        String[] dtStrs = {
        		("1999-02-01T00:00:00"),
        		("1998-01-20T00:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListBasic", !isSorted1, isSorted2);
     } // end of testListBasic

     /**
      * Test sorting with millis of second comparator.
      */
    public void testListMillis() {
        //
        List<DateTime> sl = new ArrayList<DateTime>();
        long base = 12345L * 1000L;
        sl.add( new DateTime( base + 999L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 222L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 456L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 888L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 123L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 000L, DateTimeZone.UTC ) );
        //
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, millisCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListLillis", !isSorted1, isSorted2);
    } // end of testListSecond


     /**
      * Test sorting with second comparator.
      */
    public void testListSecond() {
    	String[] dtStrs = {
    			("1999-02-01T00:00:10"),
    			("1999-02-01T00:00:30"),
    			("1999-02-01T00:00:25"),
    			("1999-02-01T00:00:18"),
    			("1999-02-01T00:00:01"),
    			("1999-02-01T00:00:59"),
    			("1999-02-01T00:00:22")
    	};
    	//
    	List<DateTime> sl = loadAList( dtStrs );
    	boolean isSorted1 = isListSorted( sl );
    	Collections.sort( sl, secondCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListSecond", !isSorted1, isSorted2);
    } // end of testListSecond

     /**
      * Test sorting with minute comparator.
      */
    public void testListMinute() {
        String[] dtStrs = {
        		("1999-02-01T00:10:00"),
        		("1999-02-01T00:30:00"),
        		("1999-02-01T00:25:00"),
        		("1999-02-01T00:18:00"),
        		("1999-02-01T00:01:00"),
        		("1999-02-01T00:59:00"),
        		("1999-02-01T00:22:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, minuteCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListMinute", !isSorted1, isSorted2);
    } // end of testListMinute

     /**
      * Test sorting with hour comparator.
      */
    public void testListHour() {
        String[] dtStrs = {
        		("1999-02-01T10:00:00"),
        		("1999-02-01T23:00:00"),
        		("1999-02-01T01:00:00"),
        		("1999-02-01T15:00:00"),
        		("1999-02-01T05:00:00"),
        		("1999-02-01T20:00:00"),
        		("1999-02-01T17:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, hourCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListHour", !isSorted1, isSorted2);
    } // end of testListHour


     /**
      * Test sorting with day of week comparator.
      */
    public void testListDOW() {
        String[] dtStrs = {
            /* 2002-04-15 = Monday */
        		("2002-04-21T10:00:00"),
        		("2002-04-16T10:00:00"),
        		("2002-04-15T10:00:00"),
        		("2002-04-17T10:00:00"),
        		("2002-04-19T10:00:00"),
        		("2002-04-18T10:00:00"),
        		("2002-04-20T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, dayOfWeekCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOW", !isSorted1, isSorted2);
    } // end of testListDOW

     /**
      * Test sorting with day of month comparator.
      */
    public void testListDOM() {
        String[] dtStrs = {
        		/* 2002-04-14 = Sunday */
        		("2002-04-20T10:00:00"),
        		("2002-04-16T10:00:00"),
        		("2002-04-15T10:00:00"),
        		("2002-04-17T10:00:00"),
        		("2002-04-19T10:00:00"),
        		("2002-04-18T10:00:00"),
        		("2002-04-14T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, dayOfMonthCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOM", !isSorted1, isSorted2);
    } // end of testListDOM

     /**
      * Test sorting with day of year comparator.
      */
    public void testListDOY() {
        String[] dtStrs = {
        		("2002-04-20T10:00:00"),
        		("2002-01-16T10:00:00"),
        		("2002-12-31T10:00:00"),
        		("2002-09-14T10:00:00"),
        		("2002-09-19T10:00:00"),
        		("2002-02-14T10:00:00"),
        		("2002-10-30T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, dayOfYearCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOY", !isSorted1, isSorted2);
    } // end of testListDOY

     /**
      * Test sorting with week of weekyear comparator.
      */
    public void testListWOW() {
        String[] dtStrs = {
        		("2002-04-01T10:00:00"),
        		("2002-01-01T10:00:00"),
        		("2002-12-01T10:00:00"),
        		("2002-09-01T10:00:00"),
        		("2002-09-01T10:00:00"),
        		("2002-02-01T10:00:00"),
        		("2002-10-01T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, weekOfWeekYearCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListWOW", !isSorted1, isSorted2);
    } // end of testListWOW

     /**
      * Test sorting with year (given week) comparator.
      */
    public void testListYOYY() {
        // ?? How to catch end conditions ??
        String[] dtStrs = {
        		("2010-04-01T10:00:00"),
        		("2002-01-01T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, weekYearCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListYOYY", !isSorted1, isSorted2);
    } // end of testListYOYY


     /**
      * Test sorting with month comparator.
      */
    public void testListMonth() {
        String[] dtStrs = {
        		("2002-04-01T10:00:00"),
        		("2002-01-01T10:00:00"),
        		("2002-12-01T10:00:00"),
        		("2002-09-01T10:00:00"),
        		("2002-09-01T10:00:00"),
        		("2002-02-01T10:00:00"),
        		("2002-10-01T10:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, monthCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListMonth", !isSorted1, isSorted2);
    } // end of testListMonth

     /**
      * Test sorting with year comparator.
      */
     public void testListYear() {
        String[] dtStrs = {
        		("1999-02-01T00:00:00"),
        		("1998-02-01T00:00:00"),
        		("2525-02-01T00:00:00"),
        		("1776-02-01T00:00:00"),
        		("1863-02-01T00:00:00"),
        		("1066-02-01T00:00:00"),
        		("2100-02-01T00:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, yearCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListYear", !isSorted1, isSorted2);
     } // end of testListYear

     /**
      * Test sorting with date only comparator.
      */
    public void testListDate() {
        String[] dtStrs = {
        		("1999-02-01T00:00:00"),
        		("1998-10-03T00:00:00"),
        		("2525-05-20T00:00:00"),
        		("1776-12-25T00:00:00"),
        		("1863-01-31T00:00:00"),
        		("1066-09-22T00:00:00"),
        		("2100-07-04T00:00:00")
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, dateCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDate", !isSorted1, isSorted2);
    } // end of testListDate

     /**
      * Test sorting with time only comparator.
      */
    public void testListTime() {
        String[] dtStrs = {
        		"1999-02-01T01:02:05",
        		"1999-02-01T22:22:22",
        		"1999-02-01T05:30:45",
        		"1999-02-01T09:17:59",
        		"1999-02-01T09:17:58",
        		"1999-02-01T15:30:00",
        		"1999-02-01T17:00:44"
        };
        //
        List<DateTime> sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, timeCompare );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListTime", !isSorted1, isSorted2);
    } // end of testListTime


    /**
     * Test comparator operation with null object(s).
     */
    public void testNullDT() {
        // null means now
        aDateTime = getADate(("2000-01-01T00:00:00"));
        assertTrue(yearCompare.compare(null, aDateTime) > 0);
        assertTrue(yearCompare.compare(aDateTime, null) < 0);
    }

    /**
     * Test comparator operation with an invalid object type.
     */
//    public void testInvalidObj() {
//        aDateTime = getADate("2000-01-01T00:00:00");
//        try {
//            yearCompare.compare("FreeBird", aDateTime);
//            fail("Invalid object failed");
//        } catch (IllegalArgumentException cce) {}
//    }

    // private convenience methods
    //-----------------------------------------------------------------------
    /**
     * Creates a date to test with.
     */
    private DateTime getADate(String date) {
    	return dtf.parseDateTime(date);
//        DateTime retDT = new DateTime(dtf.parsD,DateTimeZone.UTC);
//        return retDT;
    }

    /**
     * Load a string array.
     */
    private List<DateTime> loadAList(String[] someStrs) {
    	List<DateTime> newList = new ArrayList<DateTime>();
    	for (int i = 0; i < someStrs.length; ++i) {
    		newList.add(getADate(someStrs[i]));
    	} 
        return newList;
    }

    /**
     * Check if the list is sorted.
     */
    private boolean isListSorted(List<DateTime> tl) {
        // tl must be populated with DateTime objects.
        DateTime lhDT = tl.get(0);
        DateTime rhDT = null;
        Long lhVal = new Long( lhDT.getMillis() );
        Long rhVal = null;
        for (int i = 1; i < tl.size(); ++i) {
            rhDT = tl.get(i);
            rhVal = new Long( rhDT.getMillis() );
            if ( lhVal.compareTo( rhVal) > 0 ) return false;
            //
            lhVal = rhVal;  // swap for next iteration
            lhDT = rhDT;    // swap for next iteration
        }
        return true;
    }

}
