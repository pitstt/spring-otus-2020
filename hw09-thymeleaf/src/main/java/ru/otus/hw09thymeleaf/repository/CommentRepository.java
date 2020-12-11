package ru.otus.hw09thymeleaf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw09thymeleaf.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
