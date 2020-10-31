package ru.otus.hw08mongo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {

    @Test
    void saveAndFindById(@Autowired BookRepository bookRepository) {
        Author author = new Author("3", "Test", "Test");
        Genre genre = new Genre("3", "Test");
        Comment comment = new Comment("Test");
        Book book = new Book("3", "test", author, genre, Arrays.asList(comment));
        bookRepository.save(book);
        assertThat(bookRepository.findById("3")).isNotEmpty().hasValue(book);
    }

    @Test
    void getAll(@Autowired BookRepository bookRepository) {
        assertThat(bookRepository.findAll())
                .contains(bookRepository.findById("1").get(), bookRepository.findById("2").get());
    }

    @Test
    void createAndRemove(@Autowired BookRepository bookRepository) {
        Author author = new Author("3", "Test", "Test");
        Genre genre = new Genre("3", "Test");
        Comment comment = new Comment("Test");
        Book book = new Book("3", "test", author, genre, Arrays.asList(comment));
        bookRepository.save(book);
        assertThat(bookRepository.findById("3")).isNotEmpty().hasValue(book);
        bookRepository.deleteById(book.getId());
        assertThat(bookRepository.findById("3")).isEmpty();
    }
}