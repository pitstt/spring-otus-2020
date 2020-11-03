package ru.otus.hw08mongo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class GenreRepositoryTest {

    @Test
    void saveAndFindById(@Autowired GenreRepository genreRepository) {
        Genre genre = new Genre("3", "Test");
        genreRepository.save(genre);
        assertThat(genreRepository.findById("3")).isNotEmpty().hasValue(genre);
    }

}