package ru.otus.springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springshell.AppProperty;
import ru.otus.springshell.dao.QuestionDao;
import ru.otus.springshell.domain.Question;
import ru.otus.springshell.domain.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final Scanner scanner;

    private final MessageSource messageSource;

    private final AppProperty appProperty;

    private User user;

    @Override
    public List<Question> getAll() throws IOException {
        return dao.getAllQuestion();
    }

    @Override
    public void start() throws IOException {
        int success = 0, fail = 0;
        var answerQuestions = messageSource.getMessage("answer.questions", new String[]{}, appProperty.getLocale());
        System.out.println(answerQuestions);
        List<Question> questionList = getAll();
        for (Question question : questionList) {
            System.out.println(question.getText());
            if (scanner.nextLine().equals(question.getAnswer())) {
                success++;
            } else {
                fail++;
            }
        }
        var result = messageSource.getMessage("user.result", new String[]{user.getName(), user.getSurName(), getResultTesting(success, fail)}, appProperty.getLocale());
        System.out.println(result);
    }

    @Override
    public User createUser(String name, String surName) {
        user = new User(name, surName);
        return user;
    }

    private String getResultTesting(int success, int fail) {
        if (success > fail) {
            return "Success";
        }
        return "Fail";
    }
}
