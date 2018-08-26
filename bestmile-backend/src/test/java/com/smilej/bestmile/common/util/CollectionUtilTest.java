package com.smilej.bestmile.common.util;

import lombok.val;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionUtilTest {

    @Test
    public void should_return_empty_stream_when_passing_null() throws Exception {
        // when
        val result = CollectionUtil.nullableStream(null);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void should_return_empty_stream_when_passing_emptyList() throws Exception {
        // given
        val list = emptyList();
        // when
        val result = CollectionUtil.nullableStream(list);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void should_return_not_empty_stream_when_passing_not_emptyList() throws Exception {
        // given
        val list = singletonList("Test");
        // when
        val result = CollectionUtil.nullableStream(list);
        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    public void should_return_first_element_when_passing_not_emptyList() throws Exception {
        // given
        val list = asList("Value1", "Value2");
        // when
        val result = CollectionUtil.firstElement(list);
        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo("Value1");
    }

    @Test
    public void should_not_be_present_when_passing_emptyList() throws Exception {
        // given
        val list = emptyList();
        // when
        val result = CollectionUtil.firstElement(list);
        // then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void should_not_be_present_when_passing_null() throws Exception {
        // when
        val result = CollectionUtil.firstElement(null);
        // then
        assertThat(result.isPresent()).isFalse();
    }


}