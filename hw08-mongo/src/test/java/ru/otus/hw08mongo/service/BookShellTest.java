package ru.otus.hw08mongo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookShellTest {

    @Autowired
    private BookShell bookShell;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void createBook() {
        String result = bookShell.createBook("3", "Test", "1", "1");
        assertThat(result).isNotEmpty().matches("Книга Test Рей Брэдбери Антиутопия зарегистрирована в системе!");
    }

    @Test
    void createBookWithBadAuthorId() {
        String result = bookShell.createBook("3", "Test", "23", "1");
        assertThat(result).isNotEmpty().matches("Автор c id = 23 не зарегистрирован в системе!");
    }

    @Test
    void createBookWithBadGenreId() {
        String result = bookShell.createBook("3", "Test", "1", "13");
        assertThat(result).isNotEmpty().matches("Жанр c id = 13 не зарегистрирован в системе!");
    }

    @Test
    void getById() {
        String result = bookShell.getById("1");
        assertThat(result).isNotEmpty().matches("Вино из одуванчиков Рей Брэдбери Роман");
    }

    @Test
    void getAll() {
        String result = bookShell.getAll();
        assertThat(result).isNotEmpty().contains("Вино из одуванчиков Рей Брэдбери Роман")
                .contains("1984 Джордж Оруэлл Антиутопия");
    }

    @Test
    void removeById() {
        String result = bookShell.removeById("1");
        assertThat(result).isNotEmpty().matches("Книга с id = 1 удалена!");
    }

    @Test
    void getCommentsByBook() {
        List<Comment> result = bookShell.getCommentsByBook(bookRepository.findById("2").get());
        assertThat(result).isNotEmpty().matches(comments -> comments.size() == 1)
                .matches(comments -> comments.get(0).getText().equals("not bad"));
    }
}