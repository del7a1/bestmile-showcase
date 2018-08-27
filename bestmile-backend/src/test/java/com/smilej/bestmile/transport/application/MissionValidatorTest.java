package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.application.dto.CoordinateDto;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MissionValidatorTest {

    private MissionDto validMission;

    @Before
    public void setUp() throws Exception {
        validMission = new MissionDto();
        validMission.setPickUpCoordinate(new CoordinateDto(1, 2));
        validMission.setDropOffCoordinate(new CoordinateDto(1, 2));
        validMission.setPassengerCount(3);
    }

    @Test
    public void should_return_combined_exceptions_when_meany_fields_invalid() throws Exception {
        // given
        val mission = new MissionDto();
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(mission));
        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getSuppressed()).hasSize(3);
    }

    @Test
    public void should_do_nothing_if_newMission_isValid() throws Exception {
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(validMission));
        // then
        assertThat(throwable).isNull();
    }

    @Test
    public void should_be_invalid_when_pickUpCoordinate_isNull() throws Exception {
        // given
        val invalidMission = validMission;
        invalidMission.setPickUpCoordinate(null);
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(invalidMission));
        // then
        assertSingleException(throwable, "pickUpCoordinate must bes specified");
    }

    @Test
    public void should_be_invalid_when_dropOffCoordinate_isNull() throws Exception {
        // given
        val invalidMission = validMission;
        invalidMission.setDropOffCoordinate(null);
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(invalidMission));
        // then
        assertSingleException(throwable, "dropOffCoordinate must bes specified");
    }

    @Test
    public void should_be_invalid_when_passengerCount_is_less_than_1() throws Exception {
        // given
        val invalidMission = validMission;
        invalidMission.setPassengerCount(0);
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(invalidMission));
        // then
        assertSingleException(throwable, "There should be at lest one");
    }

    @Test
    public void should_be_invalid_when_passengerCount_is_more_than_4() throws Exception {
        // given
        val invalidMission = validMission;
        invalidMission.setPassengerCount(5);
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidNewMission(invalidMission));
        // then
        assertSingleException(throwable, "There might be max 4 passengers");
    }

    @Test
    public void should_do_nothing_miles_areValid() throws Exception {
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidMiles(1));
        // then
        assertThat(throwable).isNull();
    }

    @Test
    public void should_be_invalid_when_miles_less_than_0() throws Exception {
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidMiles(-1));
        // then
        assertSingleException(throwable, "Miles must be greater than 0");
    }

    @Test
    public void should_be_invalid_when_miles_equals_0() throws Exception {
        // when
        val throwable = catchThrowable(() ->  MissionValidator.assertIfValidMiles(0));
        // then
        assertSingleException(throwable, "Miles must be greater than 0");
    }

    private void assertSingleException(Throwable throwable, String message) {
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getSuppressed()).hasSize(1);
        assertThat(throwable.getSuppressed()[0]).hasMessage(message);
    }


}