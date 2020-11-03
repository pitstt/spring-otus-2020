package ru.otus.hw08mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw08mongo.domain.Book;

public interface BookRepository extends MongoRepository<Book,String> {
}