package com.example.springnewui.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String format(LocalDate date){
        if (date == null){
            return null;
        }
        return DATE_TIME_FORMATTER.format(date);
    }

    public static LocalDate parse(String stringDate){
        try{
            return DATE_TIME_FORMATTER.parse(stringDate, LocalDate::from);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    public static boolean isValidDate(String stringDate){
        if (DateUtils.parse(stringDate) == null){
            return false;
        } else {
            return true;
        }
    }
}
