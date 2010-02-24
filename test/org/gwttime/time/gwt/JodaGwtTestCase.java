package org.gwttime.time.gwt;

import org.gwttime.time.DateTimeZone;
import org.gwttime.time.tz.GwtZoneInfoProvider;
import org.gwttime.time.tz.Provider;

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
        return "org.gwttime.TimeTest";
    }

}
