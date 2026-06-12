package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.LoginRequest;
import com.example.rikkeibank.dto.request.RefreshTokenRequest;
import com.example.rikkeibank.dto.respone.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(
            RefreshTokenRequest request);

    void logout(String token);
}