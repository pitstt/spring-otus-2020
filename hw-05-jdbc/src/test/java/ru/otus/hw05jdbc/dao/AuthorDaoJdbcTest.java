package ru.otus.hw05jdbc.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.hw05jdbc.domain.Author;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("getById существующей в базе сущности")
    void getByIdGood() {
        Author author = authorDao.getById(1);
        Assertions.assertEquals(1, author.getId());
        Assertions.assertEquals("Рей", author.getName());
        Assertions.assertEquals("Брэдбери", author.getSurname());
    }

    @Test
    @DisplayName("getById не существующей в базе сущности")
    void getByIdWithEmptyResultDataAccessException() {
        Throwable thrown = Assertions.assertThrows(EmptyResultDataAccessException.class,
                () -> {
                    Author author = authorDao.getById(15);
                });
        Assertions.assertEquals("Incorrect result size: expected 1, actual 0", thrown.getMessage());
    }

    @Test
    @DisplayName("Создание нового автора")
    void insert(){
        Author  author = new Author(3, "Иван", "Иванов");
        authorDao.insert(author);
        Author newAuthor = authorDao.getById(3);
        Assertions.assertEquals(3, newAuthor.getId());
        Assertions.assertEquals("Иван", newAuthor.getName());
        Assertions.assertEquals("Иванов", newAuthor.getSurname());
    }
}