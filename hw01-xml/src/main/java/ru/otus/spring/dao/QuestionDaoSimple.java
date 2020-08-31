package ru.otus.spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.*;


public class QuestionDaoSimple implements QuestionDao {

    private final List<Question> questionsList;

    public QuestionDaoSimple(Resource resource) throws IOException {
        this.questionsList = mapToCSV(resource);
    }

    private List<Question> mapToCSV(Resource resource) throws IOException {
        List<Question> result = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader("text", "answer")
                .withDelimiter(';')
                .withSkipHeaderRecord()
                .parse(new InputStreamReader(resource.getInputStream()));
        for (CSVRecord record : records) {
            String text = record.get("text");
            String answer = record.get("answer");
            result.add(new Question(text,answer));
        }
        return result;
    }

    public List<Question> getAllQuestion() {
        return Collections.unmodifiableList(questionsList);
    }
}
