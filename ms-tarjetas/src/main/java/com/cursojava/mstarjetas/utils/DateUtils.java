package com.cursojava.mstarjetas.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static String getCurrentMonthYear() {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return currentDate.format(formatter);
    }

    public static String getExpirationDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate fechaVencimiento = currentDate.plusMonths(2).plusYears(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return fechaVencimiento.format(formatter);
    }

    public static Date getEndOfMonthDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        return Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
