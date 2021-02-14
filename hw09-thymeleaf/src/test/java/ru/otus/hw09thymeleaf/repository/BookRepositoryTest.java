package ru.otus.hw09thymeleaf.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.domain.Genre;
import ru.otus.hw09thymeleaf.domain.Author;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("ru.otus.hw08mongo.event")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void saveAndFindById() {
        Author author = new Author("3", "Test", "Test");
        Genre genre = new Genre("3", "Test");
        Book book = new Book("3", "test", author, genre);
        bookRepository.save(book);
        assertThat(bookRepository.findById("3")).isNotEmpty().hasValue(book);
    }

    @Test
    void getAll() {
        assertThat(bookRepository.findAll())
                .contains(bookRepository.findById("1").get(), bookRepository.findById("2").get());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void createAndRemove() {
        Author author = new Author("3", "Test", "Test");
        Genre genre = new Genre("3", "Test");
        Book book = new Book("3", "test", author, genre);
        bookRepository.save(book);
        assertThat(bookRepository.findById("3")).isNotEmpty().hasValue(book);
        bookRepository.deleteById(book.getId());
        assertThat(bookRepository.findById("3")).isEmpty();
    }
}