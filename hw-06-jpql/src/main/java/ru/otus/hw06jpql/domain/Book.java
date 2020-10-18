package ru.otus.hw06jpql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@NamedEntityGraph(name = "book-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "author_id")
    @OneToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    private Author author;

    @JoinColumn(name = "genre_id")
    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    private Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "id=" + id + " " + name + " " + author + " " + genre;
    }
}
