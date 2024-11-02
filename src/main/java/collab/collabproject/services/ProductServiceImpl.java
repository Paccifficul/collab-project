package collab.collabproject.services;

import collab.collabproject.models.Product;
import collab.collabproject.repositories.interfaces.ProductRepository;
import collab.collabproject.services.interfaces.ProductService;
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
    public void addProduct(String name, String description, double price, int count) {
        productRepository.addProduct(name, description, price, count);
    }

    @Override
    public Product updateProduct(int id, String newName, String newDesc, double newPrice, int newCount) {
        return productRepository.updateProduct(id, newName, newDesc, newPrice, newCount);
    }
}
