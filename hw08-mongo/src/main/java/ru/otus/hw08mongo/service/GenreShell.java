package ru.otus.hw08mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08mongo.domain.Genre;
import ru.otus.hw08mongo.repository.GenreRepository;


import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreRepository genreRepository;

    @Transactional
    @ShellMethod(key = "genre", value = "Введите id, название жанра (Например: genre 1 Роман)")
    public String createGenre(@ShellOption({"genreId", "id"}) String id,
                              @ShellOption({"genreName", "gn"}) String name) {
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
        return "Жанр " + genre.getName() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "genreById", value = "Введите id (Например: 1 Роман)")
    public String getById(@ShellOption({"genreId", "id"}) String id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            return genre.get().getName();
        } else {
            return "Жанр с таким id не найден!";
        }
    }

}
