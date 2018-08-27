package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.common.interator.RowIterator;
import com.smilej.bestmile.transport.application.dto.CoordinateDto;
import com.smilej.bestmile.transport.domain.Coordinate;
import lombok.val;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.smilej.bestmile.common.util.DateAndTimeUtil.toDate;
import static com.smilej.bestmile.transport.bootstrap.MissionEventParser.PICK_UP_DATE_INDEX;
import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MissionEventParserTest {

    private static final String[] TEST_ROW_DATA = new String[] {
            "1",
            "01/01/2016 09:00:00 AM",
            "01/01/2016 09:10:00 AM",
            "N",
            "1",
            "100.0",
            "200.0",
            "300.0",
            "400.0",
            "1"
    };

    @Test
    public void should_parse_valid_cells_to_MissionEvent() throws Exception {
        // given
        val objectUnderTest = new MissionEventParser(mockSingleRow(TEST_ROW_DATA));
        // when
        val result = objectUnderTest.next();
        // then
        // 6.00 AM is treated as start time, so for 09:00:00 time should be close to [current time] + 3h
        assertThat(result.getScheduledStartDate()).isCloseTo(toDate(now().plusHours(3)), 2000);
        assertThat(result.getMission().getPickUpCoordinate()).isEqualTo(new CoordinateDto(100, 200));
        assertThat(result.getMission().getDropOffCoordinate()).isEqualTo(new CoordinateDto(300, 400));
    }

    @Test(expected = DateTimeParseException.class)
    public void should_throw_exception_when_pickUp_date_invalid() throws Exception {
        // given
        TEST_ROW_DATA[PICK_UP_DATE_INDEX] = "Invalid Date";
        val objectUnderTest = new MissionEventParser(mockSingleRow(TEST_ROW_DATA));
        // when
        objectUnderTest.next();
    }

    private RowIterator mockSingleRow(String[] cells) {
        val rowIterator = mock(RowIterator.class);
        when(rowIterator.hasNext()).thenReturn(true);
        when(rowIterator.next()).thenReturn(cells);
        return rowIterator;
    }

}