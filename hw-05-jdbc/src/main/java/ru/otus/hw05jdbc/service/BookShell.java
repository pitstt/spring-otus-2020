package ru.otus.hw05jdbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05jdbc.dao.AuthorDao;
import ru.otus.hw05jdbc.dao.BookDao;
import ru.otus.hw05jdbc.dao.GenreDao;
import ru.otus.hw05jdbc.domain.Author;
import ru.otus.hw05jdbc.domain.Book;
import ru.otus.hw05jdbc.domain.Genre;

import java.util.stream.Collectors;

@Log
@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

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
        Book book = new Book(id, name, author, genre);
        bookDao.insert(book);
        return "Книга " + book.toString() + " зарегистрирована в системе!";
    }

    @ShellMethod(key = "bookById", value = "Введите id (Например: bookById 1)")
    public String getById(@ShellOption({"bookById", "id"}) long id) {
        Book book = bookDao.getById(id);
        return book.toString();
    }

    @ShellMethod(key = "getAllBook", value = "Вывод всех книг")
    public String getAll() {
        return bookDao.getAll().stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n", "", ""));
    }

    @ShellMethod(key = "removeBookById", value = "Введите id (Например: removeBookById 1)")
    public String removeById(@ShellOption({"bookId", "id"}) long id) {
        bookDao.remove(id);
        return "Книга с id = " + id + " удалена!";
    }

    private Genre getGenre(long genreId) {
        Genre genre = null;
        try {
            genre = genreDao.getById(genreId);
        } catch (EmptyResultDataAccessException e) {
            log.warning("Genre with id = " + genreId + " not fund!");
        }
        return genre;
    }

    private Author getAuthor(long id) {
        Author author = null;
        try {
            author = authorDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warning("Author with id = " + id + " not fund!");
        }
        return author;
    }

}
