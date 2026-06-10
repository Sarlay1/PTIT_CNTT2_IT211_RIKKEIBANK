package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.RegisterRequest;
import com.example.rikkeibank.dto.RegisterResponse;
import com.example.rikkeibank.dto.UpdateUserRequest;
import com.example.rikkeibank.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    RegisterResponse register(
            RegisterRequest request
    );

    Page<UserResponse> getAllUsers(
            int page,
            int size
    );

    UserResponse getUserById(
            Long id
    );

    UserResponse updateUser(
            Long id,
            UpdateUserRequest request
    );

    void deleteUser(
            Long id
    );
}