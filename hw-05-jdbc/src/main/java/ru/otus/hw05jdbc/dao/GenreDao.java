package ru.otus.hw05jdbc.dao;

import ru.otus.hw05jdbc.domain.Genre;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);
}
