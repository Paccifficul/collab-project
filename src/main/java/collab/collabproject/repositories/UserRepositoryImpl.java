package collab.collabproject.repositories;

import collab.collabproject.mappers.UserMapper;
import collab.collabproject.models.User;

import java.util.List;
import java.util.Optional;
import collab.collabproject.repositories.interfaces.UserRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_GET_USER_BY_ID =
            "select * from users where id = :id";
    private static final String SQL_GET_ALL_USERS =
            "select * from users";

    private final UserMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.query(SQL_GET_USER_BY_ID, params, userMapper).stream()
                .findFirst();
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, userMapper);
    }
}
