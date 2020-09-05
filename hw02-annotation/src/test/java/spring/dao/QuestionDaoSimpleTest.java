package spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoSimpleTest {

    private QuestionDaoSimple questionDao;

    @BeforeEach
    void setUp() throws IOException {
        questionDao = new QuestionDaoSimple(new ClassPathResource("questions.csv"));
    }

    @Test
    void getAllQuestion() throws IOException {
        assertEquals(questionDao.getAllQuestion().size(), 5);
    }
}