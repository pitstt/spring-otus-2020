package ru.otus.hw08mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw08mongo.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
