package ru.otus.hw09thymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/addAuthor")
    public String showAddAuthorPage(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "createOrUpdateAuthor";
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateAuthorPage(Model model, @PathVariable("id") String id) {
        final Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "createOrUpdateAuthor";
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "authors";
    }

    @PostMapping("/author")
    public String createOrUpdate(@ModelAttribute("author") Author author) {
        if(!StringUtils.hasText(author.getId())){
            author.setId(null);
        }
        authorService.createOrUpdate(author);
        return "redirect:/authors";
    }

    @GetMapping("/author/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        authorService.removeById(id);
        return "redirect:/authors";
    }
}
