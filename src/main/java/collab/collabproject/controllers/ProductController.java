package collab.collabproject.controllers;

import collab.collabproject.models.Product;
import collab.collabproject.services.interfaces.ProductService;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/{id:\\d+}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
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

    @PutMapping("/update/{id:\\d+}")
    public Optional<Product> updateProduct(@RequestBody Product newProduct, @PathVariable int id) {
        return productService.updateProduct(
                id,
                newProduct.name(),
                newProduct.description(),
                newProduct.article(),
                newProduct.price(),
                newProduct.count()
        );
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
