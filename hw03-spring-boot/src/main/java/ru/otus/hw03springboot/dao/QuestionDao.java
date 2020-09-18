package ru.otus.hw03springboot.dao;


import ru.otus.hw03springboot.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getAllQuestion() throws IOException;
}
