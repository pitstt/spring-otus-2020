package spring.service;


import spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws IOException;

    void start() throws IOException;
}
