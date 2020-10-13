package ru.otus.hw06jpql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06jpql.domain.Comment;
import ru.otus.hw06jpql.repository.BookRepositoryJpa;
import ru.otus.hw06jpql.repository.CommentRepositoryJpa;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentRepositoryJpa commentRepositoryJpa;

    private final BookRepositoryJpa bookRepositoryJpa;

    @Transactional
    @ShellMethod(key = "comment", value = "Введите id книги и комментарий (Например: comment 1 фывыф)")
    public String createComment(@ShellOption({"bookId", "bi"}) long bookId,
                                @ShellOption({"text", "t"}) String text) {
        Comment comment = new Comment();
        comment.setText(text);
        bookRepositoryJpa.getById(bookId).ifPresent(comment::setBook);
        commentRepositoryJpa.insertOrUpdate(comment);
        return "Комментарий " + comment.toString() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "commentById", value = "Введите id (Например: commentById 1)")
    public String getById(@ShellOption({"commentId", "id"}) long id) {
        Optional<Comment> comment = commentRepositoryJpa.getById(id);
        if (comment.isPresent()) {
            return comment.toString();
        } else {
            return "Комментарий не найден!";
        }
    }

    @Transactional
    @ShellMethod(key = "removeCommentById", value = "Введите id (Например: removeCommentById 1)")
    public String removeById(@ShellOption({"commentId", "id"}) long id) {
        commentRepositoryJpa.remove(id);
        return "Комментарий с id = " + id + " удален!";
    }
}
