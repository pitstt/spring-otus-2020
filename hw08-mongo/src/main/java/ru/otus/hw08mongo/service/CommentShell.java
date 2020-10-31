package ru.otus.hw08mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.repository.BookRepository;
import ru.otus.hw08mongo.repository.CommentRepository;


import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Transactional
    @ShellMethod(key = "comment", value = "Введите id книги и комментарий (Например: comment 1 фывыф)")
    public String createComment(@ShellOption({"text", "t"}) String text) {
        Comment comment = new Comment();
        comment.setText(text);
        commentRepository.save(comment);
        return "Комментарий " + comment.getText() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "commentById", value = "Введите id (Например: commentById 1)")
    public String getById(@ShellOption({"commentId", "id"}) String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get().getText();
        } else {
            return "Комментарий не найден!";
        }
    }

    @Transactional
    @ShellMethod(key = "removeCommentById", value = "Введите id (Например: removeCommentById 1)")
    public String removeById(@ShellOption({"commentId", "id"}) String id) {
        commentRepository.deleteById(id);
        return "Комментарий с id = " + id + " удален!";
    }
}
