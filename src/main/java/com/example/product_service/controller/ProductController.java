package com.example.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.UserProductsResponseDto;
import com.example.product_service.entity.Product;
import com.example.product_service.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Value("${custom.message}") private String customMessage;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product saveProduct(@Valid @RequestBody ProductRequest product) {
       return productService.saveProduct(product);
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }
    
    @GetMapping("/list")
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/user/{userId}")
    public UserProductsResponseDto getUserProducts(@PathVariable Long userId) {
        return productService.getUserProducts(userId);
    }
}