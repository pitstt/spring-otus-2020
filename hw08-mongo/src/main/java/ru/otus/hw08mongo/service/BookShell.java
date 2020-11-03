package ru.otus.hw08mongo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.domain.Genre;
import ru.otus.hw08mongo.repository.AuthorRepository;
import ru.otus.hw08mongo.repository.BookRepository;
import ru.otus.hw08mongo.repository.GenreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log
@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Transactional
    @ShellMethod(key = "book", value = "Введите id, название, id автора,id жанра (Например: book 1 имя 1 1)")
    public String createBook(@ShellOption({"bookId", "id"}) String id,
                             @ShellOption({"bookName", "bn"}) String name,
                             @ShellOption({"authorId", "ai"}) String authorId,
                             @ShellOption({"genreId", "di"}) String genreId) {
        Author author = getAuthor(authorId);
        if (author == null) {
            return "Автор c id = " + authorId + " не зарегистрирован в системе!";
        }
        Genre genre = getGenre(genreId);
        if (genre == null) {
            return "Жанр c id = " + genreId + " не зарегистрирован в системе!";
        }
        Book book = new Book(id, name, author, genre, Collections.emptyList());
        bookRepository.save(book);
        return "Книга " + book.getName() + " " + book.getAuthor().getName() + " " +
                book.getAuthor().getSurname() + " " + book.getGenre().getName() + " зарегистрирована в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "bookById", value = "Введите id (Например: bookById 1)")
    public String getById(@ShellOption({"bookById", "id"}) String id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return book.getName() + " " + book.getAuthor().getName() + " " +
                    book.getAuthor().getSurname() + " " + book.getGenre().getName();
        } else {
            return "Книга не найдена!";
        }
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "getAllBook", value = "Вывод всех книг")
    public String getAll() {
        return bookRepository.findAll().stream()
                .map(book -> book.getName() + " " + book.getAuthor().getName() + " " + book.getAuthor().getSurname()
                        + " " + book.getGenre().getName())
                .collect(Collectors.joining("\n", "", ""));
    }

    @Transactional
    @ShellMethod(key = "removeBookById", value = "Введите id (Например: removeBookById 1)")
    public String removeById(@ShellOption({"bookId", "id"}) String id) {
        bookRepository.deleteById(id);
        return "Книга с id = " + id + " удалена!";
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }

    private Genre getGenre(String genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    private Author getAuthor(String id) {
        return authorRepository.findById(id).orElse(null);
    }

}
