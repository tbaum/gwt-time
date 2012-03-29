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
package org.gwttime.time;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateTimeField;
import org.gwttime.time.DateTimeUtils;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.base.BasePartial;
import org.gwttime.time.gwt.JodaGwtTestCase;


/**
 * This class is a Junit unit test for YearMonthDay.
 *
 * @author Stephen Colebourne
 */
public class TestBasePartial extends JodaGwtTestCase {
    
    private long TEST_TIME_NOW = ConstantsForTesting.TEST_TIME_NOW_NO_2002;
        
    private DateTimeZone zone = null;

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(DateTimeZone.UTC);
    }

    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    //-----------------------------------------------------------------------
    public void testSetMethods() throws Throwable {
        MockPartial mock = new MockPartial();
        assertEquals(1970, mock.getYear());
        assertEquals(1, mock.getMonthOfYear());
        
        mock.setYear(2004);
        assertEquals(2004, mock.getYear());
        assertEquals(1, mock.getMonthOfYear());
        
        mock.setMonthOfYear(6);
        assertEquals(2004, mock.getYear());
        assertEquals(6, mock.getMonthOfYear());
        
        mock.set(2005, 5);
        assertEquals(2005, mock.getYear());
        assertEquals(5, mock.getMonthOfYear());
        
        try {
            mock.setMonthOfYear(0);
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(2005, mock.getYear());
        assertEquals(5, mock.getMonthOfYear());
        
        try {
            mock.setMonthOfYear(13);
            fail();
        } catch (IllegalArgumentException ex) {}
        assertEquals(2005, mock.getYear());
        assertEquals(5, mock.getMonthOfYear());
    }

    static class MockPartial extends BasePartial {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = -5166972877732819105L;

		MockPartial() {
            super(new int[] {1970, 1}, null);
        }

        protected DateTimeField getField(int index, Chronology chrono) {
            switch (index) {
                case 0:
                    return chrono.year();
                case 1:
                    return chrono.monthOfYear();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            return 2;
        }
        
        public int getYear() {
            return getValue(0);
        }
        
        public void setYear(int year) {
            setValue(0, year);
        }
        
        public int getMonthOfYear() {
            return getValue(1);
        }
        
        public void setMonthOfYear(int month) {
            setValue(1, month);
        }
        
        public void set(int year, int month) {
            setValues(new int[] {year, month});
        }
    }
}
