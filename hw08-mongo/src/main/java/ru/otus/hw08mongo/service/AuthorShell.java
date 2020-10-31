package ru.otus.hw08mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.repository.AuthorRepository;


import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorRepository authorRepository;

    @Transactional
    @ShellMethod(key = "author", value = "Введите id, имя и фамилию (Например: author 1 Иванов Иван )")
    public String createAuthor(@ShellOption({"authorId", "id"}) String id,
                               @ShellOption({"authorName", "an"}) String name,
                               @ShellOption({"authorSurname", "us"}) String surname) {
        Author author = new Author(id, name, surname);
        authorRepository.save(author);
        return "Автор " + author.getName() + " " + author.getSurname() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "authorById", value = "Введите id (Например: authorById 1)")
    public String getById(@ShellOption({"authorId", "id"}) String id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            return author.getName() + " " + author.getSurname();
        } else {
            return "Автор не найден!";
        }
    }

}
