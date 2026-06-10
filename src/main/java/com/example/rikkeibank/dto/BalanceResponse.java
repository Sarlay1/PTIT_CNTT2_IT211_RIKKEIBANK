package com.example.rikkeibank.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BalanceResponse {

    private String accountNumber;

    private BigDecimal balance;
}