package collab.collabproject.repositories;

public class SqlQueries {
    // User
    public static final String SQL_GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = :id";
    public static final String SQL_GET_ALL_USERS =
            "SELECT * FROM users";
    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE * FROM users WHERE id = :id";
    public static final String SQL_UPDATE_USER_BY_ID =
            "UPDATE users SET username= :username, email = :email, password = :password WHERE id = :id";
    public static final String SQL_INSERT_USER_BY_ID =
            "INSERT INTO users (username, email, password) VALUES (:username, :email, :password) RETURNING id";
}
