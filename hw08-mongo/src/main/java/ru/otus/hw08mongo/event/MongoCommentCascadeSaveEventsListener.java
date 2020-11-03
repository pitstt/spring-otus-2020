package ru.otus.hw08mongo.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.*;
import org.springframework.stereotype.Component;
import ru.otus.hw08mongo.domain.Book;
import ru.otus.hw08mongo.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoCommentCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();
        if (book.getComments() != null) {
            book.getComments().forEach(commentRepository::save);
        }
    }
}
