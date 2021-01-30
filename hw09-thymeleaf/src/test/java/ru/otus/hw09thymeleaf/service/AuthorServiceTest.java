package ru.otus.hw09thymeleaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw09thymeleaf.domain.Author;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void createAuthor() {
        Author author = new Author();
        author.setName("test");
        author.setSurname("test");
        Author result = authorService.createOrUpdate(author);
        assertEquals(author.getName(), result.getName());
        assertEquals(author.getSurname(), result.getSurname());
        assertNotNull(result.getId());
    }

    @Test
    void getById() {
        Author result = authorService.getById("3");
        assertEquals("Лев", result.getName());
        assertEquals("Толстой", result.getSurname());
        assertEquals("3", result.getId());
    }

    @Test
    void remove() {
        final Author author = authorService.getById("2");
        assertNotNull(author);
        authorService.removeById("2");
        assertThrows(NoSuchElementException.class, () -> authorService.getById("2"), "Author with id= 2 is absent!");
    }

    @Test
    void updateAuthor() {
        final Author author = authorService.getById("1");
        author.setName("update");
        authorService.createOrUpdate(author);
        final Author updateAuthor = authorService.getById("1");
        assertEquals(author.getName(), updateAuthor.getName());
        assertEquals(author.getSurname(), updateAuthor.getSurname());
        assertNotNull(updateAuthor.getId());
    }
}