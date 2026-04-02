package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // GET ALL
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // GET BY CATEGORY
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // ADD PRODUCT (ADMIN)
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    // DELETE PRODUCT (ADMIN)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
    }
}