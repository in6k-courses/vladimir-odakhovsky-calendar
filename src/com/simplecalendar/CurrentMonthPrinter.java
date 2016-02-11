package com.simplecalendar;

import com.simplecalendar.util.DateUtil;
import com.simplecalendar.util.OSInfo;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.TemporalAdjusters;

public class CurrentMonthPrinter {
    private final int TOTAL_MONTH_DAYS;
    private LocalDate currentDate;
    private LocalDate firstDayOfMonth;
    private int numberOfFirstMonthDay;

    public CurrentMonthPrinter() {
        currentDate = LocalDate.now();
        TOTAL_MONTH_DAYS = currentDate.getMonth().maxLength();
        numberOfFirstMonthDay = currentDate.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
        firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    public void print() {
        if (OSInfo.isUnix()) {
            printForUnix();
        }

        if (OSInfo.isWindows() || OSInfo.isMac()) {
            printForWindowsAndMac();
        }
    }

    private void printForUnix() {
        System.out.println("print for unix");
    }

    private void printForWindowsAndMac() {
        System.out.println("  Mo   Tu   We   Th   Fr   Sa   Su");

        printSpacesBeforeFirstDay();

        for (int i = 0; i < TOTAL_MONTH_DAYS; i++) {
            boolean isSelected = isToday(i) || isWeek(i);

            int dayNumber = i + 1;

            if (isSelected) {
                System.out.printf(" (%d) ", dayNumber);
            } else {
                System.out.printf("%3d  ", dayNumber);
            }

            if (isLastDay(i)) {
                System.out.println();
            }
        }
    }

    private boolean isWeek(int cntDaysFromBegin) {
        LocalDate day = firstDayOfMonth.plusDays(cntDaysFromBegin);
        return DateUtil.isWeek(day);
    }


    private boolean isToday(int cntDaysFromBegin) {
        return MonthDay.from(currentDate).getDayOfMonth() == firstDayOfMonth.plusDays(cntDaysFromBegin).getDayOfMonth();
    }


    private boolean isLastDay(int numberOfDay) {
        return (numberOfDay + numberOfFirstMonthDay) % 7 == 0;
    }

    public void printSpacesBeforeFirstDay() {
        for (int i = 1; i < numberOfFirstMonthDay; i++)
            System.out.print("   ");
    }


}
