package ru.otus.hw08mongo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw08mongo.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class CommentRepositoryTest {

    @Test
    void saveAndFindById(@Autowired CommentRepository commentRepository) {
        Comment comment = new Comment("3", "Test");
        commentRepository.save(comment);
        assertThat(commentRepository.findById("3")).isNotEmpty().hasValue(comment);
    }

}