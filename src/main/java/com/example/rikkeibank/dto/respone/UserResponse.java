package com.example.rikkeibank.dto.respone;

import com.example.rikkeibank.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private Role role;

    private Boolean isKyc;

    private Boolean enabled;
}