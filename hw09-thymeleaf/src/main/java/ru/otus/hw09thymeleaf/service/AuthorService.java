package ru.otus.hw09thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.repository.AuthorRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author getById(String id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new NoSuchElementException(String.format("Author with id= %s is absent!", id));
    }

    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

}
