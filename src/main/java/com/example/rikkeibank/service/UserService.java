package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.CreateUserRequest;
import com.example.rikkeibank.dto.request.UpdateUserRequest;
import com.example.rikkeibank.dto.respone.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// UserService
public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);
    UserResponse getById(Long id);
    UserResponse create(CreateUserRequest request);
    UserResponse update(Long id, UpdateUserRequest request);
    void delete(Long id);
}