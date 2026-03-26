package com.example.product_service.client;

import org.springframework.stereotype.Component;

import com.example.product_service.dto.UserDto;

@Component
public class UserFallback implements UserClient {
    
    @Override
    public UserDto getUserById(Long id) {
        return new UserDto(id, "Fallback User", "Service temporarily unavailable");
    }
}
