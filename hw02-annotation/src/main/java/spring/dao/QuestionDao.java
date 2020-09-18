package spring.dao;

import spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getAllQuestion() throws IOException;
}
