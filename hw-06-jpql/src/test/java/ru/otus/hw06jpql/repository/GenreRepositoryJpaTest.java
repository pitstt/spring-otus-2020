package ru.otus.hw06jpql.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06jpql.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void insert() {
        Genre genre = new Genre();
        genre.setName("name");
        Genre newGenre = genreRepositoryJpa.insertOrUpdate(genre);
        Genre expectedGenre = em.find(Genre.class, newGenre.getId());
        Assertions.assertThat(expectedGenre).isNotNull()
                .matches(g -> g.getId() == newGenre.getId())
                .matches(g -> g.getName().equals(newGenre.getName()));
    }

    @Test
    void getById() {
        Optional<Genre> optionalActualGenre = genreRepositoryJpa.getById(1L);
        Genre expectedGenre = em.find(Genre.class, 1L);
        assertThat(optionalActualGenre).isPresent().get()
                .isEqualToComparingFieldByField(expectedGenre);
    }
}