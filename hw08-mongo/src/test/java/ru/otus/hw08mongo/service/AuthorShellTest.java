package ru.otus.hw08mongo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.repository.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorShellTest {

    @Autowired
    private AuthorShell authorShell;

    @Test
    void createAuthor() {
        String result = authorShell.createAuthor("3", "Test", "Test");
        assertThat(result).isNotEmpty().matches("Автор Test Test зарегистрирован в системе!");
    }

    @Test
    void getById() {
        String result = authorShell.getById("1");
        assertThat(result).isNotEmpty().matches("Рей Брэдбери");
    }
}