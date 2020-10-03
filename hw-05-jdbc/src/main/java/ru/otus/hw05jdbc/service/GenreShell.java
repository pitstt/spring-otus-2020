package ru.otus.hw05jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05jdbc.dao.GenreDao;
import ru.otus.hw05jdbc.domain.Genre;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreDao genreDao;

    @ShellMethod(key = "genre", value = "Введите id, название жанра (Например: genre 1 Роман)")
    public String createGenre(@ShellOption({"genreId", "id"}) long id,
                              @ShellOption({"genreName", "gn"}) String name) {
        Genre genre = new Genre(id, name);
        genreDao.insert(genre);
        return "Жанр " + genre.toString() + " зарегистрирован в системе!";
    }

    @ShellMethod(key = "genreById", value = "Введите id (Например: 1 Роман)")
    public String getById(@ShellOption({"genreId", "id"}) long id) {
        Genre genre = genreDao.getById(id);
        return genre.toString();
    }

}
