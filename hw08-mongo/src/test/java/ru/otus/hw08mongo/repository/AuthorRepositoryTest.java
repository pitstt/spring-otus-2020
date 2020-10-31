package ru.otus.hw08mongo.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw08mongo.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthorRepositoryTest {

    @Test
    void saveAndFindById(@Autowired AuthorRepository authorRepository) {
        Author author = new Author("3", "Test", "Test");
        authorRepository.save(author);
        assertThat(authorRepository.findById("3")).isNotEmpty().hasValue(author);
    }

}