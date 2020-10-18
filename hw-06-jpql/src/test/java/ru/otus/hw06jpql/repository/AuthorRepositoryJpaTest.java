package ru.otus.hw06jpql.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06jpql.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void insert() {
        Author author = new Author();
        author.setName("name");
        author.setSurname("surname");
        Author newAuthor = authorRepositoryJpa.insertOrUpdate(author);
        Author expectedAuthor = em.find(Author.class, newAuthor.getId());
        Assertions.assertThat(expectedAuthor).isNotNull()
                .matches(a -> a.getId() == newAuthor.getId())
                .matches(a -> a.getName().equals(newAuthor.getName()))
                .matches(a -> a.getSurname().equals(newAuthor.getSurname()));
    }

    @Test
    void getById() {
        Optional<Author> optionalActualAuthor = authorRepositoryJpa.getById(1L);
        Author expectedAuthor = em.find(Author.class, 1L);
        assertThat(optionalActualAuthor).isPresent().get()
                .isEqualToComparingFieldByField(expectedAuthor);
    }
}