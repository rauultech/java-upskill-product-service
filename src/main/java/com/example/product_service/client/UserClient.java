package com.example.product_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.product_service.dto.UserDto;

@FeignClient(name = "user-service", fallback = UserFallback.class)
public interface UserClient {
    
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id);
}
