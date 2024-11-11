package collab.collabproject.controller;

import collab.collabproject.model.Product;
import collab.collabproject.service.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId:\\d+}")
    public Optional<Product> getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }


    @PostMapping()
    public Optional<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(
                product.name(),
                product.description(),
                product.article(),
                product.price(),
                product.count()
        );
    }

    @PutMapping("/{id:\\d+}")
    public Optional<Product> updateProduct(@RequestBody Product newProduct, @PathVariable int id) {
        return productService.updateProduct(
                id,
                newProduct.name(),
                newProduct.description(),
                newProduct.article(),
                newProduct.price()
        );
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
