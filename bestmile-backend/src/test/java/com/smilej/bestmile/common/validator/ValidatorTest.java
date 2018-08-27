package com.smilej.bestmile.common.validator;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ValidatorTest {

    @Test
    public void should_return_combined_exceptions_when_meany_fields_invalid() throws Exception {
        // when
        val throwable = catchThrowable(() ->
                Validator.of(0)
                        .check(i -> i > 10, "i is not greater than 10")
                        .check(i -> i > 9, "i is not greater than 9")
                        .check(i -> i == 0, "i is not 0")
                        .validate()
        );
        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getSuppressed()).hasSize(2);
        assertThat(throwable.getSuppressed()[0]).hasMessage("i is not greater than 10");
        assertThat(throwable.getSuppressed()[1]).hasMessage("i is not greater than 9");
    }

    @Test
    public void should_do_nothing_when_isValid() throws Exception {
        // when
        val throwable = catchThrowable(() ->
                Validator.of(0).check(i -> i == 0, "i is not 0").validate()
        );
        // then
        assertThat(throwable).isNull();
    }

}