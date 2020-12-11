package ru.otus.hw09thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09thymeleaf.domain.Comment;
import ru.otus.hw09thymeleaf.repository.CommentRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Comment getById(String id){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        }
        throw new NoSuchElementException(String.format("Comment with id= %s is absent!", id));
    }

    @Transactional
    public Comment create(Comment comment){
        return commentRepository.save(comment);
    }

}
