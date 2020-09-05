package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() throws IOException {
        return dao.getAllQuestion();
    }

    @Override
    public void start() throws IOException {
        List<Question> questionList = getAll();
        for (Question question : questionList) {
            System.out.println(question.getText());
        }
    }
}
