package ru.otus.springshell.dao;

import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDaoSimpleTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void getAllQuestion() throws IOException {
        assertEquals(1, questionDao.getAllQuestion().size());
    }

    @org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {
        @Bean
        public Reader reader() throws IOException {
            return new InputStreamReader(new FileInputStream("src/test/resources/questions.csv"));
        }

        @Bean
        public CSVFormat csvFormat() {
            return CSVFormat.DEFAULT
                    .withHeader("text", "answer")
                    .withDelimiter(';')
                    .withSkipHeaderRecord();
        }
    }
}