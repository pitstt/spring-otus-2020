package ru.otus.hw09thymeleaf.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw09thymeleaf.domain.Author;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.domain.Comment;
import ru.otus.hw09thymeleaf.domain.Genre;
import ru.otus.hw09thymeleaf.repository.AuthorRepository;
import ru.otus.hw09thymeleaf.repository.BookRepository;
import ru.otus.hw09thymeleaf.repository.CommentRepository;
import ru.otus.hw09thymeleaf.repository.GenreRepository;


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

    @ChangeSet(order = "004", id = "insertComments", author = "pitstt", runAlways = true)
    public void insertComments(BookRepository bookRepository, CommentRepository commentRepository) {
        Comment comment = new Comment("1", "good");
        Comment comment2 = new Comment("2", "bad");
        Comment comment3 = new Comment("3", "not bad");
        Comment comment4 = new Comment("4", "not bad");
        commentRepository.saveAll(Arrays.asList(comment, comment2, comment3, comment4));
    }

    @ChangeSet(order = "005", id = "insertBook", author = "pitstt", runAlways = true)
    public void insertBook(BookRepository bookRepository, GenreRepository genreRepository,
                           AuthorRepository authorRepository, CommentRepository commentRepository) {
        Book book = new Book("1", "Вино из одуванчиков", authorRepository.findById("1").get(),
                genreRepository.findById("2").get(), Arrays.asList(commentRepository.findById("1").get(),
                commentRepository.findById("2").get(), commentRepository.findById("3").get()));
        Book book2 = new Book("2", "1984", authorRepository.findById("2").get(), genreRepository.findById("1").get(),
                Arrays.asList(commentRepository.findById("4").get()));
        bookRepository.saveAll(Arrays.asList(book, book2));
    }
}
