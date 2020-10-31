package ru.otus.hw08mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.repository.BookRepository;


import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final BookRepository bookRepository;

    @Transactional
    @ShellMethod(key = "comment", value = "Введите id книги и комментарий (Например: comment 1 фывыф)")
    public String createComment(@ShellOption({"bookId", "b"}) String bookId ,@ShellOption({"text", "t"}) String text) {
        Comment comment = new Comment(text);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.addComment(comment);
            bookRepository.save(book);
            return "Комментарий " + comment.getText() + " зарегистрирован в системе!";
        }
        return "Книга с id " + bookId + " не найдена!";
    }

}
