package ru.otus.springshell.service;



import ru.otus.springshell.domain.Question;
import ru.otus.springshell.domain.User;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws IOException;

    void start() throws IOException;

    User createUser(String name, String surName);
}
