package ru.otus.hw08mongo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentShellTest {

    @Autowired
    private CommentShell commentShell;

    @Test
    void createComment() {
        String result = commentShell.createComment("1","Test");
        assertThat(result).isNotEmpty().matches("Комментарий Test зарегистрирован в системе!");
    }

    @Test
    void createCommentWithBadBookId() {
        String result = commentShell.createComment("11","Test");
        assertThat(result).isNotEmpty().matches("Книга с id 11 не найдена!");
    }

    @Test
    void getById() {
        String result = commentShell.getById("1");
        assertThat(result).isNotEmpty().matches("good");
    }

    @Test
    void removeById() {
        String result = commentShell.removeById("1");
        assertThat(result).isNotEmpty().matches("Комментарий с id = 1 удален!");
    }
}