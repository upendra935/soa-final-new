package com.epharm.product;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + id));
    }

    // ADD product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {

        if (!productRepository.existsById(id)) {
            return "Product not found";
        }

        productRepository.deleteById(id);

        return "Product deleted successfully";
    }
}