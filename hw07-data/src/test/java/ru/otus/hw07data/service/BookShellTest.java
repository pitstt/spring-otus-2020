package ru.otus.hw07data.service;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw07data.domain.Book;
import ru.otus.hw07data.domain.Comment;
import ru.otus.hw07data.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookShell.class})
class BookShellTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookShell bookShell;

    @Autowired
    private TestEntityManager em;

    @Test
    void getCommentsByBook() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        Book book = bookRepository.findById(1L).get();
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
        List<Comment> comments = bookShell.getCommentsByBook(book);
        assertEquals(3, comments.size());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
    }
}