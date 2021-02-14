package ru.otus.hw09thymeleaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw09thymeleaf.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    void getById() {
        Genre result = genreService.getById("1");
        assertEquals("Антиутопия", result.getName());
    }

    @Test
    void getAll() {
        List<Genre> result = genreService.getAll();
        assertEquals(2, result.size());
        assertEquals("Антиутопия", result.get(0).getName());
        assertEquals("Роман", result.get(1).getName());
    }
}