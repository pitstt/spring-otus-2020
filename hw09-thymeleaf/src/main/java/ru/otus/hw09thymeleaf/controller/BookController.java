package ru.otus.hw09thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.domain.Genre;
import ru.otus.hw09thymeleaf.service.AuthorService;
import ru.otus.hw09thymeleaf.service.BookService;
import ru.otus.hw09thymeleaf.service.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books.html";
    }

    @GetMapping("/addBook")
    public String showAddBookPage(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "createOrUpdateBook";
    }

    @GetMapping("/book/update/{id}")
    public String showUpdateBookPage(Model model, @PathVariable("id") String id) {
        final Book book = bookService.getById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "createOrUpdateBook";
    }

    @PostMapping("/book")
    public String createOrUpdate(@ModelAttribute("book") Book book) {
        final Author author = authorService.getById(book.getAuthor().getId());
        final Genre genre = genreService.getById(book.getGenre().getId());
        book.setAuthor(author);
        book.setGenre(genre);
        if(!StringUtils.hasText(book.getId())){
            book.setId(null);
        }
        bookService.createOrUpdate(book);
        return "redirect:/books";
    }

    @GetMapping("/book/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        bookService.removeById(id);
        return "redirect:/books";
    }
}
