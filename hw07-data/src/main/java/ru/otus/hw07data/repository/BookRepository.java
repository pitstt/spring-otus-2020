package ru.otus.hw07data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw07data.domain.Book;

public interface BookRepository extends CrudRepository<Book,Long> {
}