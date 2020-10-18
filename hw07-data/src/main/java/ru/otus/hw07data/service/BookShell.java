package ru.otus.hw07data.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07data.domain.Author;
import ru.otus.hw07data.domain.Book;
import ru.otus.hw07data.domain.Comment;
import ru.otus.hw07data.domain.Genre;
import ru.otus.hw07data.repository.AuthorRepository;
import ru.otus.hw07data.repository.BookRepository;
import ru.otus.hw07data.repository.GenreRepository;

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
    public String createAuthor(@ShellOption({"bookId", "id"}) long id,
                               @ShellOption({"bookName", "bn"}) String name,
                               @ShellOption({"authorId", "ai"}) long authorId,
                               @ShellOption({"genreId", "di"}) long genreId) {
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
        return "Книга " + book.toString() + " зарегистрирована в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "bookById", value = "Введите id (Например: bookById 1)")
    public String getById(@ShellOption({"bookById", "id"}) long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get().toString();
        } else {
            return "Книга не найдена!";
        }
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "getAllBook", value = "Вывод всех книг")
    public String getAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(Book::toString)
                .collect(Collectors.joining("\n", "", ""));
    }

    @Transactional
    @ShellMethod(key = "removeBookById", value = "Введите id (Например: removeBookById 1)")
    public String removeById(@ShellOption({"bookId", "id"}) long id) {
        bookRepository.deleteById(id);
        return "Книга с id = " + id + " удалена!";
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }

    private Genre getGenre(long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    private Author getAuthor(long id) {
        return authorRepository.findById(id).orElse(null);
    }

}
