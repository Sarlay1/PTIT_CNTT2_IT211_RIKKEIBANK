package com.example.rikkeibank.dto;

import com.example.rikkeibank.entity.KycStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KycReviewRequest {

    @NotNull(message = "Status is required")
    private KycStatus status; // APPROVED or REJECTED

    private String rejectionReason; // Required if status = REJECTED
}