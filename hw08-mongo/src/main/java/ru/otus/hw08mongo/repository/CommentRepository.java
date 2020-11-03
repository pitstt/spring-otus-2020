package ru.otus.hw08mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw08mongo.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
