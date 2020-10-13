package ru.otus.hw06jpql.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw06jpql.domain.Book;
import ru.otus.hw06jpql.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({CommentRepositoryJpa.class})
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void insertOrUpdate() {
        Book book = new Book();
        book.setId(1);
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setText("2222");

        Comment newComment = commentRepository.insertOrUpdate(comment);
        Comment expectedComment = em.find(Comment.class, newComment.getId());
        Assertions.assertThat(expectedComment).isNotNull()
                .matches(c -> c.getId() == newComment.getId())
                .matches(c -> c.getBook().getId() == newComment.getBook().getId())
                .matches(c -> c.getText().equals(newComment.getText()));
    }

    @Test
    void getById() {
        Optional<Comment> optionalActualComment = commentRepository.getById(1L);
        Comment expectedComment = em.find(Comment.class, 1L);
        assertThat(optionalActualComment).isPresent().get()
                .isEqualToComparingFieldByField(expectedComment);
    }

    @Test
    void remove() {
        Comment firstComment = em.find(Comment.class, 1L);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        commentRepository.remove(1);
        Comment deletedComment = em.find(Comment.class, 1L);

        assertThat(deletedComment).isNull();
    }
}