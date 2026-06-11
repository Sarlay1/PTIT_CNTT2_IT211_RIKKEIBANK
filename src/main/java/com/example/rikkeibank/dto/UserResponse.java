package com.example.rikkeibank.dto;

import com.example.rikkeibank.entity.Role;
import com.example.rikkeibank.entity.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String nationalId;
    private UserStatus status;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}