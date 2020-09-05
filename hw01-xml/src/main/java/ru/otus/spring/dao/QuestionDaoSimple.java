package ru.otus.spring.dao;

import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.*;

@Log
public class QuestionDaoSimple implements QuestionDao {

    private final Reader reader;

    public QuestionDaoSimple(Resource resource) throws IOException {
        this.reader = new InputStreamReader(resource.getInputStream());
    }

    private List<Question> mapToCSV() throws IOException {
        List<Question> result = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader("text", "answer")
                .withDelimiter(';')
                .withSkipHeaderRecord()
                .parse(reader);
        for (CSVRecord record : records) {
            String text = record.get("text");
            String answer = record.get("answer");
            result.add(new Question(text,answer));
        }
        return result;
    }

    public List<Question> getAllQuestion() throws IOException {
        return  mapToCSV();
    }
}
