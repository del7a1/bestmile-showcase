package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.common.interator.CsvRowIterator;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;

@Component
public class CsvMissionEventRepository {

    private final Resource eventsResource;

    public CsvMissionEventRepository(@Value("classpath:mission-events.csv") Resource eventsResource) {
        this.eventsResource = eventsResource;
    }

    public Stream<MissionEvent> getAll() {
        val rowIterator = new MissionEventParser(new CsvRowIterator(eventsResource));
        return StreamSupport.stream(spliteratorUnknownSize(rowIterator, 0), false);
    }

}
