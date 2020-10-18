package ru.otus.hw07data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.hw07data.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
