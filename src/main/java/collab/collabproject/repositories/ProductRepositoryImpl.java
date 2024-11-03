package collab.collabproject.repositories;

import collab.collabproject.mappers.ProductMapper;
import collab.collabproject.models.Product;
import collab.collabproject.models.User;
import collab.collabproject.repositories.interfaces.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper productMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(ProductMapper productMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.productMapper = productMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        var params = new MapSqlParameterSource();

        params.addValue("id", id);

        return jdbcTemplate.query(SqlQueries.SQL_GET_PRODUCT_BY_ID, params, productMapper)
                .stream()
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SqlQueries.SQL_GET_ALL_PRODUCTS, productMapper);
    }

    @Override
    public Optional<Product> addProduct(String name, String description, String article, double price, int count) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("description", description);
        params.addValue("article", article);
        params.addValue("price", price);
        params.addValue("count", count);

        try {
            var keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(SqlQueries.SQL_INSERT_PRODUCT_BY_ID, params, keyHolder);
            int prodId = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return Optional.of(new Product(
                    prodId,
                    name,
                    description,
                    article,
                    price,
                    count
            ));
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> updateProduct(int id, String newName, String newDesc, String newArticle, double newPrice) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", newName);
        params.addValue("description", newDesc);
        params.addValue("article", newArticle);
        params.addValue("price", newPrice);

        try {
            jdbcTemplate.update(SqlQueries.SQL_UPDATE_PRODUCT_BY_ID, params);

            return getProductById(id);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public ResponseEntity<?> deleteProductById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);

        if (getProductById(id).isEmpty()) {
            return new ResponseEntity<>("Продукт " + id + " отсутствует, удаление прервано.", HttpStatus.OK);
        }

        jdbcTemplate.update(SqlQueries.SQL_DELETE_PRODUCT_BY_ID, params);

        return new ResponseEntity<>("Продукт " + id + " удален успешно", HttpStatus.OK);
    }
}
