package collab.collabproject.services.interfaces;

import collab.collabproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(int id);
    List<Product> getAllProducts();
    Optional<Product> addProduct(String name, String description, String article, double price, int count);
    Optional<Product> updateProduct(int id, String newName, String newDesc, String newArticle, double newPrice, int newCount);
    String deleteProduct(int id);
}
