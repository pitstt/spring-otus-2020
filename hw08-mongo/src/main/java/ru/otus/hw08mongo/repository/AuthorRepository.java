package ru.otus.hw08mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw08mongo.domain.Author;

public interface AuthorRepository extends MongoRepository<Author,String> {
}
