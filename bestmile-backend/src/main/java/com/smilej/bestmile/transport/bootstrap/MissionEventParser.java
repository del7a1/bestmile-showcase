package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.common.interator.RowIterator;
import com.smilej.bestmile.transport.application.dto.CoordinateDto;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import lombok.val;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;

import static com.smilej.bestmile.common.util.DateAndTimeUtil.toDate;
import static com.smilej.bestmile.common.util.RowParserUtil.*;

class MissionEventParser implements Iterator<MissionEvent> {

    /**
     * Data was exported at 6 AM
     */
    public static final int EXPORT_DATA_HOUR = 6;

    public static final int PICK_UP_DATE_INDEX = 1;
    public static final int PICK_UP_LNG_INDEX = 5;
    public static final int PICK_UP_LAT_INDEX = 6;
    public static final int DROP_OFF_LNG_INDEX = 7;
    public static final int DROP_OFF_LAT_INDEX = 8;
    public static final int PASSENGER_COUNT_INDEX = 9;

    private final RowIterator rowIterator;

    MissionEventParser(RowIterator rowIterator) {
        this.rowIterator = rowIterator;

        if (rowIterator.hasNext()) {
            rowIterator.next(); // skip headers
        } else {
            throw new UnsupportedOperationException("Csv file is empty");
        }
    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext();
    }

    @Override
    public MissionEvent next() {
        if (hasNext()) {
            val cells = rowIterator.next();
            return new MissionEvent(
                    parseToCurrentDate(PICK_UP_DATE_INDEX, cells),
                    parseMission(cells)
            );
        }
        return null;
    }

    private MissionDto parseMission(String[] cells) {
        val mission = new MissionDto();
        mission.setPickUpCoordinate(parseCoordinate(PICK_UP_LNG_INDEX, PICK_UP_LAT_INDEX, cells));
        mission.setDropOffCoordinate(parseCoordinate(DROP_OFF_LNG_INDEX, DROP_OFF_LAT_INDEX, cells));
        mission.setPassengerCount(asInteger(PASSENGER_COUNT_INDEX, cells));
        return mission;
    }

    private CoordinateDto parseCoordinate(int longitudeIndex, int latitudeIndex, String[] cells) {
        return new CoordinateDto(asDouble(longitudeIndex, cells), asDouble(latitudeIndex, cells));
    }

    private Date parseToCurrentDate(int columnIndex, String[] cells) {
        val time = asTime(columnIndex, cells);
        val delayTime = LocalDateTime.now()
                .plusHours(time.getHour() - EXPORT_DATA_HOUR)
                .plusMinutes(time.getMinute())
                .plusSeconds(time.getSecond());
        return toDate(delayTime);
    }

}