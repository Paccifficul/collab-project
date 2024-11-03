package collab.collabproject.repositories;

import collab.collabproject.mappers.UserMapper;
import collab.collabproject.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import collab.collabproject.repositories.interfaces.UserRepository;
import org.springframework.dao.DataAccessException;
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
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
