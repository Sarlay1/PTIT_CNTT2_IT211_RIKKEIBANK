package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.RegisterRequest;
import com.example.rikkeibank.dto.RegisterResponse;
import com.example.rikkeibank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }
}