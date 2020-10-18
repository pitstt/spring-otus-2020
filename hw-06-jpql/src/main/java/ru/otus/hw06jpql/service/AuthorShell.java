package ru.otus.hw06jpql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06jpql.repository.AuthorRepository;
import ru.otus.hw06jpql.domain.Author;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorRepository authorRepository;

    @Transactional
    @ShellMethod(key = "author", value = "Введите id, имя и фамилию (Например: author 1 Иванов Иван )")
    public String createAuthor(@ShellOption({"authorId", "id"}) long id,
                               @ShellOption({"authorName", "an"}) String name,
                               @ShellOption({"authorSurname", "us"}) String surname) {
        Author author = new Author(id, name, surname);
        authorRepository.insertOrUpdate(author);
        return "Автор " + author.toString() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "authorById", value = "Введите id (Например: authorById 1)")
    public String getById(@ShellOption({"authorId", "id"}) long id) {
        Optional<Author> author = authorRepository.getById(id);
        if (author.isPresent()) {
            return author.toString();
        } else {
            return "Автор не найден!";
        }
    }

}
