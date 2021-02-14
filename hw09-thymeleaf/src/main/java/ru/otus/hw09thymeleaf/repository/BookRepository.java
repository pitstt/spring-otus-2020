package ru.otus.hw09thymeleaf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw09thymeleaf.domain.Book;

public interface BookRepository extends MongoRepository<Book,String> {
}