package com.example.rikkeibank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;
    private String phoneNumber;
    private String fullName;
    private String username;
    private String email;
}