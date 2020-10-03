package ru.otus.hw05jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05jdbc.dao.AuthorDao;
import ru.otus.hw05jdbc.domain.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorDao authorDao;

    @ShellMethod(key = "author", value = "Введите id, имя и фамилию (Например: author 1 Иванов Иван )")
    public String createAuthor(@ShellOption({"authorId", "id"}) long id,
                               @ShellOption({"authorName", "an"}) String name,
                               @ShellOption({"authorSurname", "us"}) String surname) {
        Author author = new Author(id, name, surname);
        authorDao.insert(author);
        return "Автор " + author.toString() + " зарегистрирован в системе!";
    }

    @ShellMethod(key = "authorById", value = "Введите id (Например: authorById 1)")
    public String getById(@ShellOption({"authorId", "id"}) long id) {
        Author author = authorDao.getById(id);
        return author.toString();
    }

}
