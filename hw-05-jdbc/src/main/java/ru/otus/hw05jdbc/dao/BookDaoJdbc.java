package ru.otus.hw05jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05jdbc.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private static final String INSERT_QUERY = "insert into book (id, `name`, `author_id`,`genre_id`) values (:id, :name, :author_id, :genre_id)";

    private static final String GET_BY_ID_QUERY = "select id, name, author_id, genre_id from book where id = :id";

    private static final String GET_ALL_QUERY = "select id, name, author_id, genre_id from book";

    private static final String REMOVE_BY_ID_QUERY = "delete from book where id = :id";


    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                GET_ALL_QUERY, new BookMapper(authorDao, genreDao));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                GET_BY_ID_QUERY, params, new BookMapper(authorDao, genreDao));
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        paramMap.put("name", book.getName());
        paramMap.put("author_id", book.getAuthor().getId());
        paramMap.put("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(INSERT_QUERY, paramMap);
    }

    @Override
    public void remove(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(REMOVE_BY_ID_QUERY, params);
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {

        private final AuthorDao authorDao;

        private final GenreDao genreDao;

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            return new Book(id, name, authorDao.getById(authorId), genreDao.getById(genreId));
        }
    }
}
