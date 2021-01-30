package ru.otus.hw09thymeleaf.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.repository.AuthorRepository;


import java.util.Arrays;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "pitstt", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthor", author = "pitstt", runAlways = true)
    public void insertAuthor(AuthorRepository authorRepository) {
        Author reyAuthor = new Author("1", "Рей", "Брэдбери");
        Author georgeAuthor = new Author("2", "Джордж", "Оруэлл");
        Author levAuthor = new Author("3", "Лев", "Толстой");
        authorRepository.saveAll(Arrays.asList(reyAuthor, georgeAuthor, levAuthor));
    }

}
