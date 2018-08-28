package com.smilej.bestmile.common.interator;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CsvRowIterator implements RowIterator {

    private static final String SEPARATOR = ",";

    private final BufferedReader reader;

    private String nextLine;

    @SneakyThrows
    public CsvRowIterator(Resource tsvResource) {
        this.reader = new BufferedReader(new InputStreamReader(tsvResource.getInputStream()));
        updateNextLine();
    }

    public boolean hasNext() {
        return nextLine != null;
    }

    public String[] next() {
        if (hasNext()) {
            String[] line = nextLine.split(SEPARATOR);
            updateNextLine();
            return line;
        }
        return new String[0];
    }

    @SneakyThrows
    private void updateNextLine() {
        nextLine = reader.readLine();
        while (nextLine != null && nextLine.isEmpty()) {
            nextLine = reader.readLine();
        }
    }
    
    @Override
    public void close() throws Exception {
        reader.close();
    }
    
}
