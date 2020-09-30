package ru.otus.springshell.dao;



import ru.otus.springshell.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> getAllQuestion() throws IOException;
}
