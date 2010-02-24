package org.joda.time.gwt;

import org.joda.time.DateTimeZone;
import org.joda.time.tz.GwtZoneInfoProvider;
import org.joda.time.tz.Provider;

import com.google.gwt.i18n.client.constants.TimeZoneConstants;
import com.google.gwt.junit.client.GWTTestCase;

public class JodaGwtTestCase extends GWTTestCase {
    
    private static Provider originalProvider;
    protected TimeZoneConstants timeZones;

    protected void gwtSetUp() throws Exception {
        originalProvider = DateTimeZone.getProvider();
        DateTimeZone.setProvider(GwtZoneInfoProvider.singleton());
        timeZones = GwtZoneInfoProvider.singleton().getTimeZoneConstants();
    }
    
    protected void gwtTearDown() throws Exception {
        DateTimeZone.setProvider(originalProvider);
    }

    @Override
    public String getModuleName() {
        return "org.joda.TimeTest";
    }

}
