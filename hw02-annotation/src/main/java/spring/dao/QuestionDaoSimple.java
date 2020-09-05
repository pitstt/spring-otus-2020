package spring.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import spring.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.yml")
public class QuestionDaoSimple implements QuestionDao {

    private final Reader reader;

    public QuestionDaoSimple(@Value("${resource}") Resource resource) throws IOException {
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
            result.add(new Question(text, answer));
        }
        return result;
    }

    public List<Question> getAllQuestion() throws IOException {
        return mapToCSV();
    }
}
