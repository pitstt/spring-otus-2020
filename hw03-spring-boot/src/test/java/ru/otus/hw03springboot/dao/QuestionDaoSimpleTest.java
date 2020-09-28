package ru.otus.hw03springboot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDaoSimpleTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void getAllQuestion() throws IOException {
        assertEquals(1, questionDao.getAllQuestion().size());
    }
}