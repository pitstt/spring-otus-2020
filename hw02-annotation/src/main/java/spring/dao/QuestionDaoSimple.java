package spring.dao;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import spring.domain.Question;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class QuestionDaoSimple implements QuestionDao {

    private final Reader reader;

    private final CSVFormat csvFormat;

    @Override
    public List<Question> getAllQuestion() throws IOException {
        return mapToCSV();
    }

    private List<Question> mapToCSV() throws IOException {
        List<Question> result = new ArrayList<>();
        Iterable<CSVRecord> records = csvFormat.parse(reader).getRecords();
        for (CSVRecord record : records) {
            String text = record.get("text");
            String answer = record.get("answer");
            result.add(new Question(text, answer));
        }
        return result;
    }
}
