package ru.otus.hw05jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private static final String INSERT_QUERY = "insert into genre (id, `name`) values (:id, :name)";

    private static final String GET_BY_ID_QUERY = "select id, name, from genre where id = :id";

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert(Genre genre) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", genre.getId());
        paramMap.put("name", genre.getName());
        namedParameterJdbcOperations.update(INSERT_QUERY, paramMap);
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                GET_BY_ID_QUERY, params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
