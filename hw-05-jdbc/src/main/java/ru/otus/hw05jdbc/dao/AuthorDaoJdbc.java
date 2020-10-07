package ru.otus.hw05jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05jdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private static final String INSERT_QUERY = "insert into author (id, `name`, `surname`) values (:id, :name, :surname)";

    private static final String GET_BY_ID_QUERY = "select id, name, surname from author where id = :id";

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert(Author author) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", author.getId());
        paramMap.put("name", author.getName());
        paramMap.put("surname", author.getSurname());
        namedParameterJdbcOperations.update(INSERT_QUERY, paramMap);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                GET_BY_ID_QUERY, params, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            return new Author(id, name, surname);
        }
    }
}
