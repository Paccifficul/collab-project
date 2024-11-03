package collab.collabproject.repository.interfaces;

import collab.collabproject.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductById(int id);
    List<Product> getAllProducts();
    Optional<Product> addProduct(String name, String description, String article, double price, int count);
    Optional<Product> updateProduct(int id, String newName, String newDesc, String newArticle, double newPrice);
    ResponseEntity<?> deleteProductById(int id);
}
