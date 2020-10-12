package ru.otus.hw06jpql.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw06jpql.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment insertOrUpdate(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getCommentsByBookId(long id){
        TypedQuery<Comment> query = em.createQuery("select c " +
                        "from Comment c " +
                        "where c.book.id = :id",
                Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void remove(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
