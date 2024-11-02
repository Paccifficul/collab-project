package collab.collabproject.services.interfaces;

import collab.collabproject.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(int id);
    List<Product> getAllProducts();
    void addProduct(String name, String description, double price, int count);
    Product updateProduct(int id, String newName, String newDesc, double newPrice, int newCount);
}
