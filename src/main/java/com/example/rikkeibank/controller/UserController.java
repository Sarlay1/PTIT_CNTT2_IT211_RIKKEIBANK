package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.request.CreateUserRequest;
import com.example.rikkeibank.dto.request.UpdateUserRequest;
import com.example.rikkeibank.dto.respone.ApiResponse;
import com.example.rikkeibank.dto.respone.UserResponse;
import com.example.rikkeibank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return userService.getAll(
                PageRequest.of(page, size)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(
            @PathVariable Long id) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User found")
                .data(userService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid @RequestBody CreateUserRequest request) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User created")
                .data(userService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User updated")
                .data(userService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(
            @PathVariable Long id) {

        userService.delete(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("User deleted")
                .data("Deleted")
                .build();
    }
}