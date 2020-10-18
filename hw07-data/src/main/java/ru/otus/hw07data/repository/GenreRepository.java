package ru.otus.hw07data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw07data.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
