package ru.otus.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import ru.otus.springshell.AppProperty;
import ru.otus.springshell.dao.QuestionDao;
import ru.otus.springshell.dao.QuestionDaoSimple;
import ru.otus.springshell.domain.Question;
import ru.otus.springshell.domain.User;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {

    private QuestionDao questionDao;

    private QuestionService questionService;

    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        questionDao = mock(QuestionDaoSimple.class);
        questionService = new QuestionServiceImpl(questionDao, scanner, mock(MessageSource.class),
                mock(AppProperty.class));
        questionService.createUser("name", "surname");
    }

    @Test
    void getAll() throws IOException {
        when(questionDao.getAllQuestion()).thenReturn(Collections.singletonList(new Question("2+2=?", "4")));
        assertEquals(1, questionService.getAll().size());
    }

    @Test
    void createUser() {
        User user = questionService.createUser("name", "surname");
        assertEquals("name", user.getName());
        assertEquals("surname", user.getSurName());
    }

    @Test
    void start() throws IOException {
        questionService.start();
    }

}