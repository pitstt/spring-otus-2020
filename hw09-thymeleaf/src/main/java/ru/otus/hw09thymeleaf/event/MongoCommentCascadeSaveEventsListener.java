package ru.otus.hw09thymeleaf.event;

import com.mongodb.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.hw09thymeleaf.domain.Book;
import ru.otus.hw09thymeleaf.repository.CommentRepository;

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
