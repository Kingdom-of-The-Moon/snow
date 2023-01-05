package org.moon.snow;

import java.util.Calendar;

public class Snow {

    public static final boolean SNOW;

    static {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        SNOW = (month == Calendar.DECEMBER && day > 18) || (month == Calendar.JANUARY && day < 5);
    }
}
