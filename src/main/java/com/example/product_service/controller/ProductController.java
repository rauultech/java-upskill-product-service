package com.example.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @GetMapping("/list")
    public List<String> getProducts() {
        return List.of("Product 1", "Product 2", "Product 3");
    }
    
}
