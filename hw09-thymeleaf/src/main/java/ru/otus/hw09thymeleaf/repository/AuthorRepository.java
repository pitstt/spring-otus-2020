package ru.otus.hw09thymeleaf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw09thymeleaf.domain.Author;

public interface AuthorRepository extends MongoRepository<Author,String> {
}
