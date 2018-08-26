package com.smilej.bestmile.transport.bootstrap;

import lombok.val;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvMissionEventRepositoryTest {

    private static final Resource TEST_RESOURCE = new ClassPathResource("test-mission-events.csv");

    @Test
    public void should_parse_valid_file() throws Exception {
        // given
        val objectUnderTest = new CsvMissionEventRepository(TEST_RESOURCE);
        // when
        val result = objectUnderTest.getAll().findFirst().get();
        // then
        assertThat(result.getMission()).isNotNull();
        assertThat(result.getScheduledStartDate()).isNotNull();
        assertThat(result.getScheduledEndDate()).isNotNull();
    }

}