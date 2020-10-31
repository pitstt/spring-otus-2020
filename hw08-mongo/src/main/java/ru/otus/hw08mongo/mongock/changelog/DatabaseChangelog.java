package ru.otus.hw08mongo.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw08mongo.domain.Author;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.domain.Comment;
import ru.otus.hw08mongo.domain.Genre;
import ru.otus.hw08mongo.repository.AuthorRepository;
import ru.otus.hw08mongo.repository.BookRepository;
import ru.otus.hw08mongo.repository.GenreRepository;

import java.util.Arrays;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "pitstt", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenre", author = "pitstt", runAlways = true)
    public void insertGenre(GenreRepository genreRepository) {
        Genre genre = new Genre("1", "Антиутопия");
        Genre genre2 = new Genre("2", "Роман");
        genreRepository.saveAll(Arrays.asList(genre, genre2));
    }

    @ChangeSet(order = "003", id = "insertAuthor", author = "pitstt", runAlways = true)
    public void insertAuthor(AuthorRepository authorRepository) {
        Author reyAuthor = new Author("1", "Рей", "Брэдбери");
        Author georgeAuthor = new Author("2", "Джордж", "Оруэлл");
        authorRepository.saveAll(Arrays.asList(reyAuthor, georgeAuthor));
    }


    @ChangeSet(order = "004", id = "insertBook", author = "pitstt", runAlways = true)
    public void insertBook(BookRepository bookRepository, GenreRepository genreRepository,
                           AuthorRepository authorRepository) {
        Comment comment = new Comment("good");
        Comment comment2 = new Comment("bad");
        Comment comment3 = new Comment("not bad");
        Comment comment4 = new Comment("not bad");
        Book book = new Book("1", "Вино из одуванчиков", authorRepository.findById("1").get(),
                genreRepository.findById("2").get(), Arrays.asList(comment,
                comment2, comment3));
        Book book2 = new Book("2", "1984", authorRepository.findById("2").get(), genreRepository.findById("1").get(),
                Arrays.asList(comment4));
        bookRepository.saveAll(Arrays.asList(book, book2));
    }
}
