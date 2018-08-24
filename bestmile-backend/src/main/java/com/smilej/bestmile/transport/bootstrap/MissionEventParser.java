package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.common.interator.RowIterator;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import lombok.val;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;

import static com.smilej.bestmile.common.util.DateAndTimeUtil.convertToDate;
import static com.smilej.bestmile.common.util.RowParserUtil.asDouble;
import static com.smilej.bestmile.common.util.RowParserUtil.asTime;

public class MissionEventParser implements Iterator<MissionEvent> {

    /**
     * Data was exported at 6 AM
     */
    public static final int EXPORT_DATA_HOUR = 6;


    private static final int PICK_UP_DATE_INDEX = 1;
    private static final int DROP_OFF_DATE_INDEX = 2;
    private static final int PICK_UP_LNG_INDEX = 5;
    private static final int PICK_UP_LAT_INDEX = 6;
    private static final int DROP_OFF_LNG_INDEX = 7;
    private static final int DROP_OFF_LAT_INDEX = 8;

    private final RowIterator rowIterator;

    public MissionEventParser(RowIterator rowIterator) {
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
                    parseToCurrentDate(DROP_OFF_DATE_INDEX, cells),
                    parseMission(cells)
            );
        }
        return null;
    }

    private Mission parseMission(String[] cells) {
        return new Mission(
                new Coordinate(
                        asDouble(PICK_UP_LNG_INDEX, cells),
                        asDouble(PICK_UP_LAT_INDEX, cells)
                ),
                new Coordinate(
                        asDouble(DROP_OFF_LNG_INDEX, cells),
                        asDouble(DROP_OFF_LAT_INDEX, cells)
                ),
                1
        );
    }

    private Date parseToCurrentDate(int columnIndex, String[] cells) {
        val time = asTime(columnIndex, cells);
        val currentTime = LocalTime.now()
                .plusHours(time.getHour() - EXPORT_DATA_HOUR)
                .plusMinutes(time.getMinute())
                .plusSeconds(time.getSecond());
        return convertToDate(currentTime.atDate(LocalDate.now()));
    }


}
