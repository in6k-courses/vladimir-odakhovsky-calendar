package com.simplecalendar;

import com.simplecalendar.util.DateUtil;
import com.simplecalendar.util.OSInfo;
import com.simplecalendar.util.UnixPrinter;

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
        System.out.println(" Mo  Tu  We  Th  Fr  Sa  Su");

        printSpacesBeforeFirstDay();

        for (int i = 0; i < TOTAL_MONTH_DAYS; i++) {

            if(isToday(i)){
                UnixPrinter.printConsoleGreen(String.format(" %2d ", i + 1));
            }else if (isWeek(i)){
                UnixPrinter.printConsoleRed(String.format(" %2d ", i + 1));
            }else {
                System.out.printf(" %2d ", i + 1);
            }

            if (isLastDay(i)) {
                System.out.println();
            }
        }
    }

    private void printForWindowsAndMac() {
        System.out.println("  Mo   Tu   We   Th   Fr   Sa   Su");

        printSpacesBeforeFirstDay();

        for (int i = 0; i < TOTAL_MONTH_DAYS; i++) {

            if (isToday(i) || isWeek(i)) {
                System.out.printf(" (%d) ", i + 1);
            } else {
                System.out.printf("%3d  ", i + 1);
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
