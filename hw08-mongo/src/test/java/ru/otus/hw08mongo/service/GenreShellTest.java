package ru.otus.hw08mongo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreShellTest {

    @Autowired
    private GenreShell genreShell;

    @Test
    void createGenre() {
        String result = genreShell.createGenre("3", "Test");
        assertThat(result).isNotEmpty().matches("Жанр Test зарегистрирован в системе!");
    }

    @Test
    void getById() {
        String result = genreShell.getById("1");
        assertThat(result).isNotEmpty().matches("Антиутопия");
    }
}