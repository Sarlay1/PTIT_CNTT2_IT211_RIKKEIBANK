package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.LoginRequest;
import com.example.rikkeibank.dto.request.RefreshTokenRequest;
import com.example.rikkeibank.dto.respone.LoginResponse;
import com.example.rikkeibank.dto.request.ChangePinRequest;
import com.example.rikkeibank.dto.request.ForgotPasswordRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(
            RefreshTokenRequest request);

    void logout(String token);
    void changePin(ChangePinRequest request);

    void forgotPassword(ForgotPasswordRequest request);

}