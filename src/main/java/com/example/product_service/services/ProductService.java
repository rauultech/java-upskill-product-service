package com.example.product_service.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product_service.client.UserClient;
import com.example.product_service.dto.ProductDto;
import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.UserDto;
import com.example.product_service.dto.UserProductsResponseDto;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserClient userClient;
    
    public ProductService(ProductRepository productRepository, UserClient userClient) {
        this.productRepository = productRepository;
        this.userClient = userClient;
    }

    public Product saveProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }   

    public UserProductsResponseDto getUserProducts(Long userId) {
        UserDto user = userClient.getUserById(userId);
        //TODO: Fetch products for the user from the database and convert to ProductDto
        var products = List.of(new ProductDto(1L, "Product 1"), new ProductDto(2L, "Product 2"));
        return new UserProductsResponseDto(user, products);
    }
}

