package com.example.rikkeibank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Invalid Vietnamese phone number")
    private String phoneNumber;

    private String address;
}