package ru.otus.hw05jdbc.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw05jdbc.domain.Book;

import java.util.List;


@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @Test
    void getAll() {
        List<Book> books = bookDao.getAll();
        Assertions.assertEquals(2, books.size());

        Assertions.assertEquals(1, books.get(0).getId());
        Assertions.assertEquals("Вино из одуванчиков", books.get(0).getName());
        Assertions.assertEquals(1, books.get(0).getAuthor().getId());
        Assertions.assertEquals("Рей", books.get(0).getAuthor().getName());
        Assertions.assertEquals("Брэдбери", books.get(0).getAuthor().getSurname());
        Assertions.assertEquals(2, books.get(0).getGenre().getId());
        Assertions.assertEquals("Роман", books.get(0).getGenre().getName());

        Assertions.assertEquals(2, books.get(1).getId());
        Assertions.assertEquals("1984", books.get(1).getName());
        Assertions.assertEquals(2, books.get(1).getAuthor().getId());
        Assertions.assertEquals("Джордж", books.get(1).getAuthor().getName());
        Assertions.assertEquals("Оруэлл", books.get(1).getAuthor().getSurname());
        Assertions.assertEquals(1, books.get(1).getGenre().getId());
        Assertions.assertEquals("Антиутопия", books.get(1).getGenre().getName());
    }

    @Test
    void getById() {
        Book book = bookDao.getById(1);
        Assertions.assertEquals(1, book.getId());
        Assertions.assertEquals("Вино из одуванчиков", book.getName());
        Assertions.assertEquals(1, book.getAuthor().getId());
        Assertions.assertEquals("Рей", book.getAuthor().getName());
        Assertions.assertEquals("Брэдбери", book.getAuthor().getSurname());
        Assertions.assertEquals(2, book.getGenre().getId());
        Assertions.assertEquals("Роман", book.getGenre().getName());
    }

    @Test
    void insert() {
        Book book = new Book(3,"newBook",authorDao.getById(1),genreDao.getById(2));
        bookDao.insert(book);
        Book newBook = bookDao.getById(3);
        Assertions.assertEquals(3, newBook.getId());
        Assertions.assertEquals("newBook", newBook.getName());
        Assertions.assertEquals(1, newBook.getAuthor().getId());
        Assertions.assertEquals("Рей", newBook.getAuthor().getName());
        Assertions.assertEquals("Брэдбери", newBook.getAuthor().getSurname());
        Assertions.assertEquals(2, newBook.getGenre().getId());
        Assertions.assertEquals("Роман", newBook.getGenre().getName());
    }

    @Test
    void remove() {
        bookDao.remove(2);
        List<Book> books = bookDao.getAll();
        Assertions.assertEquals(1,books.size());
        Assertions.assertEquals(1,books.get(0).getId());
    }
}