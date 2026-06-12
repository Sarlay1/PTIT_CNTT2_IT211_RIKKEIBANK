package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.request.LoginRequest;
import com.example.rikkeibank.dto.request.LogoutRequest;
import com.example.rikkeibank.dto.request.RefreshTokenRequest;
import com.example.rikkeibank.dto.respone.ApiResponse;
import com.example.rikkeibank.dto.respone.LoginResponse;
import com.example.rikkeibank.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login successful")
                .data(authService.login(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<LoginResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Refresh token successful")
                .data(authService.refreshToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(
            @Valid @RequestBody LogoutRequest request) {

        authService.logout(request.getToken());

        return ApiResponse.<String>builder()
                .success(true)
                .message("Logout successful")
                .data("Token revoked")
                .build();
    }
}