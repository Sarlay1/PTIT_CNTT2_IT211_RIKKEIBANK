package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.request.LoginRequest;
import com.example.rikkeibank.dto.request.RefreshTokenRequest;
import com.example.rikkeibank.dto.respone.LoginResponse;
import com.example.rikkeibank.entity.RefreshToken;
import com.example.rikkeibank.entity.RevokedToken;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.exception.BadRequestException;
import com.example.rikkeibank.exception.ResourceNotFoundException;
import com.example.rikkeibank.repository.RefreshTokenRepository;
import com.example.rikkeibank.repository.RevokedTokenRepository;
import com.example.rikkeibank.repository.UserRepository;
import com.example.rikkeibank.security.JwtProvider;
import com.example.rikkeibank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final RevokedTokenRepository revokedTokenRepository;

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        String accessToken =
                jwtProvider.generateToken(
                        user.getUsername()
                );

        String refreshTokenValue =
                java.util.UUID.randomUUID()
                        .toString();

        RefreshToken refreshToken =
                refreshTokenRepository
                        .findByUser(user)
                        .orElse(
                                RefreshToken.builder()
                                        .user(user)
                                        .build()
                        );

        refreshToken.setToken(refreshTokenValue);

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusDays(1)
        );

        refreshTokenRepository.save(refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenValue)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public LoginResponse refreshToken(
            RefreshTokenRequest request
    ) {

        RefreshToken refreshToken =
                refreshTokenRepository
                        .findByToken(
                                request.getRefreshToken()
                        )
                        .orElseThrow(() ->
                                new BadRequestException(
                                        "Invalid refresh token"
                                ));

        if (refreshToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            throw new BadRequestException(
                    "Refresh token expired"
            );
        }

        String accessToken =
                jwtProvider.generateToken(
                        refreshToken.getUser()
                                .getUsername()
                );

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(
                        refreshToken.getToken()
                )
                .tokenType("Bearer")
                .build();
    }

    @Override
    public void logout(String token) {

        RevokedToken revokedToken =
                RevokedToken.builder()
                        .token(token)
                        .expiredAt(
                                LocalDateTime.now()
                                        .plusDays(1)
                        )
                        .build();

        revokedTokenRepository.save(
                revokedToken
        );
    }
}