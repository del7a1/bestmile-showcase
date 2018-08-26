package com.smilej.bestmile.common.util;

import lombok.val;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class DateAndTimeUtilTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void should_convert_LocalDateTime_to_Date() throws Exception {
        // given
        val dateTime = LocalDateTime.parse("1986-04-08 12:30", FORMATTER);
        // when
        val result = DateAndTimeUtil.toDate(dateTime);
        // then
        assertThat(result).isEqualTo("1986-04-08T12:30:00.000");
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_passing_null() throws Exception {
        DateAndTimeUtil.toDate(null);
    }


}