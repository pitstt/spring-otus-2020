package ru.otus.hw06jpql.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06jpql.domain.Author;
import ru.otus.hw06jpql.domain.Book;
import ru.otus.hw06jpql.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BookRepositoryJpa.class})
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void getAll() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        List<Book> books = bookRepositoryJpa.getAll();
        assertThat(books).isNotNull().hasSize(2)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getGenre() != null)
                .allMatch(s -> s.getAuthor() != null);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }

    @Test
    void getById() {
        Optional<Book> optionalActualBook = bookRepositoryJpa.getById(1L);
        Book expectedBook = em.find(Book.class, 1L);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @Test
    void insert() {
        Author author = new Author();
        author.setName("name");
        author.setSurname("surname");
        Genre genre = new Genre();
        genre.setName("name");

        Book book = new Book();
        book.setName("new");
        book.setAuthor(author);
        book.setGenre(genre);

        Book newBook = bookRepositoryJpa.insertOrUpdate(book);
        Book expectedBook = em.find(Book.class, newBook.getId());
        Assertions.assertThat(expectedBook).isNotNull()
                .matches(b -> b.getId() == newBook.getId())
                .matches(b -> b.getName().equals(newBook.getName()))
                .matches(b -> b.getAuthor().equals(newBook.getAuthor()))
                .matches(b -> b.getGenre().equals(newBook.getGenre()));
    }

    @Test
    void remove() {
        Book firstBook = em.find(Book.class, 1L);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        bookRepositoryJpa.remove(1);
        Book deletedBook = em.find(Book.class, 1L);

        assertThat(deletedBook).isNull();
    }
}