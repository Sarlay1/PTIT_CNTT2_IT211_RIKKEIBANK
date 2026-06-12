package com.example.rikkeibank.dto.respone;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;

    private String fromAccount;

    private String toAccount;

    private BigDecimal amount;

    private String transactionType;

    private String description;

    private LocalDateTime createdAt;
}