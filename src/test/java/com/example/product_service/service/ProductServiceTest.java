package com.example.product_service.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import com.example.product_service.client.UserClient;
import com.example.product_service.dto.*;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.services.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldSaveProduct() {

        ProductRequest request = new ProductRequest();
        request.setName("Test Product");

        Product savedProduct = new Product();
        savedProduct.setName("Test Product");

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        Product result = productService.saveProduct(request);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldReturnProductWhenExists() {

        long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Product A");

        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(id);

        assertNotNull(result);
        assertEquals("Product A", result.getName());

        verify(productRepository).findById(id);
    }

    @Test
    void shouldReturnNullWhenProductNotFound() {

        long id = 2L;

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        Product result = productService.getProductById(id);

        assertNull(result);

        verify(productRepository).findById(id);
    }

    @Test
    void shouldReturnAllProducts() {

        Pageable pageable = PageRequest.of(0, 10);

        Product product = new Product();
        product.setId(1L);
        product.setName("P1");

        Page<Product> page = new PageImpl<>(List.of(product));

        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertEquals(1, result.getContent().size());

        verify(productRepository).findAll(pageable);
    }

    @Test
    void shouldReturnUserProducts() {

        Long userId = 1L;

        UserDto user = new UserDto(userId, "Rahul", "Some info");

        when(userClient.getUserById(userId)).thenReturn(user);

        UserProductsResponseDto result =
                productService.getUserProducts(userId);

        assertNotNull(result);
        assertEquals("Rahul", result.user().name());
        assertEquals(2, result.products().size());

        verify(userClient).getUserById(userId);
    }
}