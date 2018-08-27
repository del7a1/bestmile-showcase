package com.smilej.bestmile.common.util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.google.common.base.Preconditions.checkArgument;

@UtilityClass
public class RowParserUtil {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm:ss a");

    public static Integer asInteger(int columnIndex, String[] cells) {
        val value = getValue(columnIndex, cells);
        return Integer.valueOf(value);
    }

    public static Double asDouble(int columnIndex, String[] cells) {
        val value = getValue(columnIndex, cells);
        return Double.valueOf(value);
    }

    public static LocalTime asTime(int columnIndex, String[] cells) {
        return asTime(columnIndex, cells, TIME_FORMATTER);
    }

    public static LocalTime asTime(int columnIndex, String[] cells, DateTimeFormatter formatter) {
        val value = getValue(columnIndex, cells);
        return LocalTime.parse(value, TIME_FORMATTER);
    }

    private static String getValue(int columnIndex, String[] cells) {
        checkArgument(cells != null && columnIndex < cells.length);
        return cells[columnIndex];
    }

}