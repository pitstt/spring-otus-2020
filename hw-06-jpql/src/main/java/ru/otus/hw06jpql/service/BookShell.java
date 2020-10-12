package ru.otus.hw06jpql.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06jpql.repository.AuthorRepository;
import ru.otus.hw06jpql.repository.BookRepository;
import ru.otus.hw06jpql.repository.GenreRepository;
import ru.otus.hw06jpql.domain.Author;
import ru.otus.hw06jpql.domain.Book;
import ru.otus.hw06jpql.domain.Genre;

import java.util.Optional;
import java.util.stream.Collectors;

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
        Book book = new Book(id, name, author, genre);
        bookRepository.insertOrUpdate(book);
        return "Книга " + book.toString() + " зарегистрирована в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "bookById", value = "Введите id (Например: bookById 1)")
    public String getById(@ShellOption({"bookById", "id"}) long id) {
        Optional<Book> book = bookRepository.getById(id);
        if (book.isPresent()) {
            return book.get().toString();
        } else {
            return "Книга не найдена!";
        }
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "getAllBook", value = "Вывод всех книг")
    public String getAll() {
        return bookRepository.getAll().stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n", "", ""));
    }

    @Transactional
    @ShellMethod(key = "removeBookById", value = "Введите id (Например: removeBookById 1)")
    public String removeById(@ShellOption({"bookId", "id"}) long id) {
        bookRepository.remove(id);
        return "Книга с id = " + id + " удалена!";
    }

    private Genre getGenre(long genreId) {
        return genreRepository.getById(genreId).orElse(null);
    }

    private Author getAuthor(long id) {
        return authorRepository.getById(id).orElse(null);
    }

}
