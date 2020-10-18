package ru.otus.hw06jpql.repository;


import ru.otus.hw06jpql.domain.Author;

import java.util.Optional;

public interface AuthorRepository {

    Author insertOrUpdate(Author author);

    Optional<Author> getById(long id);
}
