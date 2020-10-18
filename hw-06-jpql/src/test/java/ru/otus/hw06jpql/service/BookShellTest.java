package ru.otus.hw06jpql.service;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06jpql.domain.Book;
import ru.otus.hw06jpql.domain.Comment;
import ru.otus.hw06jpql.repository.AuthorRepositoryJpa;
import ru.otus.hw06jpql.repository.BookRepositoryJpa;
import ru.otus.hw06jpql.repository.GenreRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class, BookShell.class})
class BookShellTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private BookShell bookShell;

    @Autowired
    private TestEntityManager em;

    @Test
    void getCommentsByBook() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        Book book = bookRepositoryJpa.getById(1L).get();
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
        List<Comment> comments = bookShell.getCommentsByBook(book);
        assertEquals(3, comments.size());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
    }
}