package ru.otus.hw09thymeleaf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw09thymeleaf.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
