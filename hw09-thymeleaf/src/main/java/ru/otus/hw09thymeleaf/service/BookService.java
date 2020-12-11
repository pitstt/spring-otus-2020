package ru.otus.hw09thymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Book getById(String id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new NoSuchElementException(String.format("Book with id= %s is absent!", id));
    }

    @Transactional
    public Book createOrUpdate(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public void removeById(String id){
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
}
