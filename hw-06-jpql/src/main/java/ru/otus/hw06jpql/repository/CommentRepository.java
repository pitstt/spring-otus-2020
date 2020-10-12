package ru.otus.hw06jpql.repository;

import ru.otus.hw06jpql.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment insertOrUpdate(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getCommentsByBookId(long id);

    void remove(long id);

}
