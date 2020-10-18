package ru.otus.hw06jpql.repository;


import ru.otus.hw06jpql.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAll();

    Optional<Book> getById(long id);

    Book insertOrUpdate(Book book);

    void remove(long id);
}