package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.UpdateUserRequest;
import com.example.rikkeibank.dto.UserResponse;
import com.example.rikkeibank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{id}")
    public UserResponse getById(
            @PathVariable Long id
    ) {

        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {

        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        userService.deleteUser(id);

        return "Delete Success";
    }
}