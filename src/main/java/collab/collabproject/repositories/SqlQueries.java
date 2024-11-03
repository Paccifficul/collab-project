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

    // Product
    public static final String SQL_GET_PRODUCT_BY_ID =
            "SELECT * FROM products WHERE id = :id";
    public static final String SQL_GET_ALL_PRODUCTS =
            "SELECT * FROM products";
    public static final String SQL_INSERT_PRODUCT_BY_ID =
            "INSERT INTO products (name, description, article, price, count) VALUES (:name, :description, :article, :price, :count) RETURNING id";
    public static final String SQL_UPDATE_PRODUCT_BY_ID =
            "UPDATE products SET name = :name, description = :description, article = :article, price = :price WHERE id = :id";
    public static final String SQL_DELETE_PRODUCT_BY_ID =
            "DELETE FROM products WHERE id = :id";
}
