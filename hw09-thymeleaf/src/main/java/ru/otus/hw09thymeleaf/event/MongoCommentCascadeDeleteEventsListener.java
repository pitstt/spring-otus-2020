package ru.otus.hw09thymeleaf.event;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.repository.BookRepository;
import ru.otus.hw09thymeleaf.repository.CommentRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MongoCommentCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> commentRepository.deleteAll(value.getComments()));
    }

}
