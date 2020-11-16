package com.thoughtworks.capability.gtb;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

    public static long getDaysBetweenNextLaborDay(LocalDate date) {
        LocalDate laborDay = LocalDate.of(date.getYear(), 5, 1);
        if (date.isBefore(laborDay)) {
            return DAYS.between(date, laborDay);
        } else {
            return DAYS.between(date, laborDay.plusYears(1));
        }
    }
}
