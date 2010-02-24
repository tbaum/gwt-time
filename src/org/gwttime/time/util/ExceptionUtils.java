package org.gwttime.time.util;

public class ExceptionUtils {
    
    private ExceptionUtils() {}

    public static UnsupportedOperationException unsupportedInGwt() {
        return new UnsupportedOperationException("Code is not ported to GWT");
    }
}
