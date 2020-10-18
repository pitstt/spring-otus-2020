package ru.otus.hw07data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07data.domain.Genre;
import ru.otus.hw07data.repository.GenreRepository;

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
        genreRepository.save(genre);
        return "Жанр " + genre.toString() + " зарегистрирован в системе!";
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "genreById", value = "Введите id (Например: 1 Роман)")
    public String getById(@ShellOption({"genreId", "id"}) long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            return genre.get().toString();
        } else {
            return "Жанр с таким id не найден!";
        }
    }

}
