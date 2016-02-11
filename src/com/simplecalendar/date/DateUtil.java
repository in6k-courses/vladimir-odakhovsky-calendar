package com.simplecalendar.date;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {

    public static boolean isWeek(LocalDate localDate){
        String dayName = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();
        return dayName.equals("sunday") || dayName.equals("saturday");
    }
}
