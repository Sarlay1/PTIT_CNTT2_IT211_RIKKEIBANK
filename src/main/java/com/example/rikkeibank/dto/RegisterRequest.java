package com.example.rikkeibank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be 4-50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Invalid Vietnamese phone number")
    private String phoneNumber;

    @NotBlank(message = "National ID is required")
    @Pattern(regexp = "^[0-9]{9,12}$", message = "National ID must be 9 or 12 digits")
    private String nationalId;

    private String address;
}