package com.server.cms.framework.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

    public static LocalDate getConvertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime getConvertDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDate getConvertDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime getConvertDateTime(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }

    public static String getConvertDateToString(LocalDate date) {
        if(date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getConvertDateTimeToString(LocalDateTime dateTime) {
        return getConvertDateTimeToString(dateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getConvertDateTimeToString(LocalDateTime dateTime, String pattern) {
        if(dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

}
