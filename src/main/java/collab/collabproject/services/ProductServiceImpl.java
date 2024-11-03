package collab.collabproject.services;

import collab.collabproject.models.Product;
import collab.collabproject.repositories.interfaces.ProductRepository;
import collab.collabproject.services.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Optional<Product> addProduct(String name, String description, String article, double price, int count) {
        return productRepository.addProduct(name, description, article, price, count);
    }

    @Override
    public Optional<Product> updateProduct(int id, String newName, String newDesc, String newArticle, double newPrice) {
        return productRepository.updateProduct(id, newName, newDesc, newArticle, newPrice);
    }

    @Override
    public ResponseEntity<?> deleteProduct(int id) {
        return productRepository.deleteProductById(id);
    }
}
