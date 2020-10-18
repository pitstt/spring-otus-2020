package ru.otus.hw06jpql.repository;


import ru.otus.hw06jpql.domain.Genre;

import java.util.Optional;

public interface GenreRepository {

    Genre insertOrUpdate(Genre genre);

    Optional<Genre> getById(long id);
}
