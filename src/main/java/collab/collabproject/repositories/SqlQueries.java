package collab.collabproject.repositories;

public class SqlQueries {
    // User
    public static final String SQL_GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = :id";
    public static final String SQL_GET_ALL_USERS =
            "SELECT * FROM users";
    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE * FROM users WHERE id = :id";
}
