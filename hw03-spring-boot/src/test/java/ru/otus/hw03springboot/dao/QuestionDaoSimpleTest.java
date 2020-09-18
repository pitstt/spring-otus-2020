package ru.otus.hw03springboot.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class QuestionDaoSimpleTest {

    private QuestionDaoSimple questionDao;

    private Reader reader;

    private CSVFormat csvFormat;

    @BeforeEach
    void setUp() {
        csvFormat = mock(CSVFormat.class);
        reader = mock(InputStreamReader.class);
        questionDao = new QuestionDaoSimple(reader, csvFormat);
    }

    @Test
    void getAllQuestion() throws IOException {
        CSVParser parser = mock(CSVParser.class);
        CSVRecord record = mock(CSVRecord.class);

        given(csvFormat.parse(reader)).willReturn(parser);
        given(parser.getRecords()).willReturn(Collections.singletonList(record));
        given(record.get("text")).willReturn("2+2=?");
        given(record.get("answer")).willReturn("4");

        assertEquals(1, questionDao.getAllQuestion().size());
    }
}