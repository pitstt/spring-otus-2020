package ru.otus.hw03springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw03springboot.AppProperty;
import ru.otus.hw03springboot.dao.QuestionDao;
import ru.otus.hw03springboot.domain.Question;

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

    @Override
    public List<Question> getAll() throws IOException {
        return dao.getAllQuestion();
    }

    @Override
    public void start() throws IOException {
        int success = 0, fail = 0;
        var userName = messageSource.getMessage("user.name", new String[]{}, appProperty.getLocale());
        System.out.println(userName);
        String name = scanner.nextLine();
        var userSurname = messageSource.getMessage("user.surname", new String[]{}, appProperty.getLocale());
        System.out.println(userSurname);
        String surname = scanner.nextLine();
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
        var result = messageSource.getMessage("user.result", new String[]{name, surname, getResultTesting(success, fail)}, appProperty.getLocale());
        System.out.println(result);
    }

    private String getResultTesting(int success, int fail) {
        if (success > fail) {
            return "Success";
        }
        return "Fail";
    }
}
