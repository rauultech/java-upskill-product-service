package com.example.product_service.dto;

import java.util.List;

public record UserProductsResponseDto(
    UserDto user,
    List<ProductDto> products
){}
