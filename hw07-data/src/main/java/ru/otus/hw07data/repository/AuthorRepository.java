package ru.otus.hw07data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw07data.domain.Author;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
