package collab.collabproject.repositories;

import collab.collabproject.mappers.UserMapper;
import collab.collabproject.models.Product;
import collab.collabproject.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import collab.collabproject.repositories.interfaces.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(
            UserMapper userMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        this.userMapper = userMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> getUserById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(SqlQueries.SQL_GET_USER_BY_ID, params, userMapper).stream()
                .findFirst();
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query(SqlQueries.SQL_GET_ALL_USERS, userMapper);
    }

    @Override
    public Optional<User> addUser(String username, String email, String password) {
        var params = new MapSqlParameterSource();
        params.addValue("username", username);
        params.addValue("email", email);
        params.addValue("password", password);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(SqlQueries.SQL_INSERT_USER_BY_ID, params, keyHolder);
            int userId = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return Optional.of(new User(userId, username, email, password));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> updateUser(int id, String username, String email) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("username", username);
        params.addValue("email", email);

        try {
            jdbcTemplate.update(SqlQueries.SQL_UPDATE_USER_BY_ID, params);
            return getUserById(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);

        if (getUserById(id).isEmpty()) {
            return new ResponseEntity<>("Пользователь " + id + " отсутствует, удаление прервано.", HttpStatus.OK);
        }

        jdbcTemplate.update(SqlQueries.SQL_DELETE_USER_BY_ID, params);
        return new ResponseEntity<>("Продукт " + id + " удален успешно", HttpStatus.OK);
    }
}
