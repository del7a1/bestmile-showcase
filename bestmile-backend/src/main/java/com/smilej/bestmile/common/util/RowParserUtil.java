package com.smilej.bestmile.common.util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class RowParserUtil {

    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm:ss a");

    public static Double asDouble(int columnIndex, String[] cells) {
        val value = asString(columnIndex, cells);
        return Double.valueOf(value);
    }

    public static LocalTime asTime(int columnIndex, String[] cells) {
        val value = asString(columnIndex, cells);
        return LocalTime.parse(value, TIME_FORMATTER);
    }

    public static String asString(int columnIndex, String[] cells) {
        return cells[columnIndex];
    }

}
