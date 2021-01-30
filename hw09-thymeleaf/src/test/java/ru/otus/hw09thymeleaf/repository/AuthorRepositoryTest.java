package ru.otus.hw09thymeleaf.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw09thymeleaf.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthorRepositoryTest {

    @Test
    void saveAndFindById(@Autowired AuthorRepository authorRepository) {
        Author author = new Author("3", "Test", "Test");
        authorRepository.save(author);
        assertThat(authorRepository.findById("3")).isNotEmpty().hasValue(author);
    }

    @Test
    void saveAndDeleteById(@Autowired AuthorRepository authorRepository) {
        Author author = new Author("3", "Test", "Test");
        authorRepository.save(author);
        assertThat(authorRepository.findById("3")).isNotEmpty().hasValue(author);
        authorRepository.deleteById("3");
        assertThat(authorRepository.findById("3")).isEmpty();
    }

    @Test
    void saveAndUpdate(@Autowired AuthorRepository authorRepository) {
        Author author = new Author("3", "Test", "Test");
        authorRepository.save(author);
        assertThat(authorRepository.findById("3")).isNotEmpty().hasValue(author);
        author.setName("Update");
        authorRepository.save(author);
        assertThat(authorRepository.findById("3")).isNotEmpty().hasValue(author);
    }

}