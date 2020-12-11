package ru.otus.hw09thymeleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {

    @MongoId
    private String id;
    private String name;
    private Author author;
    private Genre genre;

    @DBRef
    private List<Comment> comments;

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
