package com.smilej.bestmile.common.util;

import lombok.val;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RowParserUtilTest {

    @Test
    public void should_parse_to_Integer() throws Exception {
        // when
        val result = RowParserUtil.asInteger(0, new String[] {"1"});
        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_parse_to_Double() throws Exception {
        // when
        val result = RowParserUtil.asDouble(0, new String[] {"1"});
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void should_parse_to_LocalTime() throws Exception {
        // when
        val result = RowParserUtil.asTime(0, new String[] {"10/9/2018 12:12:12 PM"});
        // then
        assertThat(result).isEqualTo(LocalTime.parse("12:12:12"));
    }

    @Test
    public void should_throw_IllegalArgumentException_when_index_out_of_bound() throws Exception {
        // given
        val emptyArray = new String[0];
        List<TestableMethod> methodsToTest = asList(
                RowParserUtil::asDouble,
                RowParserUtil::asInteger,
                RowParserUtil::asTime
        );
        // then
        methodsToTest.forEach(method -> {
            val throwable = catchThrowable(() -> method.test(1, emptyArray));
            assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    public void should_throw_IllegalArgumentException_when_passed_array_is_null() throws Exception {
        // given
        List<TestableMethod> methodsToTest = asList(
                RowParserUtil::asDouble,
                RowParserUtil::asInteger,
                RowParserUtil::asTime
        );
        // then
        methodsToTest.forEach(method -> {
            val throwable = catchThrowable(() -> method.test(1, null));
            assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        });
    }

    @FunctionalInterface
    private interface TestableMethod {
        Object test(int columnIndex, String[] cells);
    }

}