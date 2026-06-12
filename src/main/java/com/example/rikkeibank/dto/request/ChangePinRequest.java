package com.example.rikkeibank.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePinRequest {

    private String username;

    @NotBlank(message = "Old pin is required")
    private String oldPin;

    @NotBlank(message = "New pin is required")
    @Pattern(
            regexp = "\\d{6}",
            message = "PIN must contain exactly 6 digits"
    )
    private String newPin;
}