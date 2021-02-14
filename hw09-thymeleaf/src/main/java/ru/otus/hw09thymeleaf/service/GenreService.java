package ru.otus.hw09thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.domain.Genre;
import ru.otus.hw09thymeleaf.repository.BookRepository;
import ru.otus.hw09thymeleaf.repository.GenreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genre getById(String id){
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            return genre.get();
        }
        throw new NoSuchElementException(String.format("Genre with id= %s is absent!", id));}
}
