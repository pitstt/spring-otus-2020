package ru.otus.hw05jdbc.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.hw05jdbc.domain.Genre;


@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("getById существующей в базе сущности")
    void getById() {
        Genre genre = genreDao.getById(1);
        Assertions.assertEquals(1, genre.getId());
        Assertions.assertEquals("Антиутопия", genre.getName());
    }

    @Test
    @DisplayName("getById не существующей в базе сущности")
    void getByIdWithEmptyResultDataAccessException() {
        Throwable thrown = Assertions.assertThrows(EmptyResultDataAccessException.class,
                () -> {
                    Genre genre = genreDao.getById(15);
                });
        Assertions.assertEquals("Incorrect result size: expected 1, actual 0", thrown.getMessage());
    }

    @Test
    @DisplayName("Создание нового жанра")
    void insert() {
        Genre genre = new Genre(3,"Фэнтези");
        genreDao.insert(genre);
        Genre newGenre  = genreDao.getById(3);
        Assertions.assertEquals(3, newGenre.getId());
        Assertions.assertEquals("Фэнтези", newGenre.getName());
    }

}