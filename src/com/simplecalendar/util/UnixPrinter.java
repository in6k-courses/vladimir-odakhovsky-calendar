package com.simplecalendar.util;


public class UnixPrinter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void printConsoleRed(String text){
        print(ANSI_RED, text);
    }

    public static void printConsoleGreen(String text){
        print(ANSI_GREEN, text);
    }

    private static void print(String color, String text){
        System.out.print(color + text + ANSI_RESET);
    }
}
