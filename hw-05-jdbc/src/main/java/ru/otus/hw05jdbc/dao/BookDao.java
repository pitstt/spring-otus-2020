package ru.otus.hw05jdbc.dao;

import ru.otus.hw05jdbc.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(long id);

    void insert(Book book);

    void remove(long id);
}
