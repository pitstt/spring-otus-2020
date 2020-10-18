package ru.otus.hw06jpql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06jpql.repository.GenreRepository;
import ru.otus.hw06jpql.domain.Genre;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreRepository genreRepository;

    @Transactional
    @ShellMethod(key = "genre", value = "Введите id, название жанра (Например: genre 1 Роман)")
    public String createGenre(@ShellOption({"genreId", "id"}) long id,
                              @ShellOption({"genreName", "gn"}) String name) {
        Genre genre = new Genre(id, name);
        genreRepository.insertOrUpdate(genre);
        return "Жанр " + genre.toString() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "genreById", value = "Введите id (Например: 1 Роман)")
    public String getById(@ShellOption({"genreId", "id"}) long id) {
        Optional<Genre> genre = genreRepository.getById(id);
        if (genre.isPresent()) {
            return genre.get().toString();
        } else {
            return "Жанр с таким id не найден!";
        }
    }

}
