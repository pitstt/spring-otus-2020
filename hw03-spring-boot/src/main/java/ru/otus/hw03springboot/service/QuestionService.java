package ru.otus.hw03springboot.service;


import ru.otus.hw03springboot.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws IOException;

    void start() throws IOException;
}
