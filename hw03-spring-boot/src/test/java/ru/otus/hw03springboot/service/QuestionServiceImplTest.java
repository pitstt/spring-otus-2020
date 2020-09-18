package ru.otus.hw03springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.otus.hw03springboot.AppProperty;
import ru.otus.hw03springboot.dao.QuestionDao;
import ru.otus.hw03springboot.dao.QuestionDaoSimple;
import ru.otus.hw03springboot.domain.Question;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {

    private QuestionDao questionDao;

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionDao = mock(QuestionDaoSimple.class);
        questionService = new QuestionServiceImpl(questionDao, mock(Scanner.class), mock(MessageSource.class), mock(AppProperty.class));
    }

    @Test
    void getAll() throws IOException {
        when(questionDao.getAllQuestion()).thenReturn(Collections.singletonList(new Question("2+2=?", "4")));
        assertEquals(1, questionService.getAll().size());
    }

    @Test
    void start() throws IOException {
        questionService.start();
    }
}