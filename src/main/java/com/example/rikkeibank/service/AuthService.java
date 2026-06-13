package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.*;
import com.example.rikkeibank.dto.respone.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(
            RefreshTokenRequest request);

    void logout(String token);
    void changePin(ChangePinRequest request);

    void forgotPassword(ForgotPasswordRequest request);
    void register(RegisterRequest request);

}