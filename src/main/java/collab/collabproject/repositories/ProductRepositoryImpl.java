package collab.collabproject.repositories;

import collab.collabproject.mappers.ProductMapper;
import collab.collabproject.models.Product;
import collab.collabproject.repositories.interfaces.ProductRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SqlQueries.SQL_GET_ALL_PRODUCTS, productMapper);
    }

    @Override
    public void addProduct(String name, String description, double price, int count) {

    }

    @Override
    public Product updateProduct(int id, String newName, String newDesc, double newPrice, int newCount) {
        return null;
    }
}
