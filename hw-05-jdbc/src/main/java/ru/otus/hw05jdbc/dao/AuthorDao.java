package ru.otus.hw05jdbc.dao;

import ru.otus.hw05jdbc.domain.Author;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);
}
