package ru.otus.hw09thymeleaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.domain.Genre;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Test
    void createBook() {
        Book book = new Book();
        book.setName("test");
        final Author author = authorService.getById("1");
        final Genre genre = genreService.getById("1");
        book.setAuthor(author);
        book.setGenre(genre);
        Book result = bookService.createOrUpdate(book);
        assertEquals(book.getName(), result.getName());
        assertEquals(book.getAuthor(), author);
        assertEquals(book.getGenre(), genre);
        assertNotNull(result.getId());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void getById() {
        Book result = bookService.getById("1");
        assertEquals("Вино из одуванчиков", result.getName());
        assertEquals("Брэдбери", result.getAuthor().getSurname());
        assertEquals("Роман", result.getGenre().getName());
        assertEquals("1", result.getId());
    }

    @Test
    void remove() {
        final Book book = bookService.getById("2");
        assertNotNull(book);
        bookService.removeById("2");
        assertThrows(NoSuchElementException.class, () -> bookService.getById("2"), "Book with id= 2 is absent!");
    }

    @Test
    void updateAuthor() {
        final Book book = bookService.getById("1");
        book.setName("update");
        bookService.createOrUpdate(book);
        final Book updateBook= bookService.getById("1");
        assertEquals(book.getName(), updateBook.getName());
        assertEquals(book.getAuthor(), updateBook.getAuthor());
        assertEquals(book.getGenre(), updateBook.getGenre());
        assertEquals(book.getId(), updateBook.getId());
    }
}