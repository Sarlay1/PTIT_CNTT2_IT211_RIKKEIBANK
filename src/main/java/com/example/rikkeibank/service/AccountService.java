package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.BalanceResponse;

public interface AccountService {

    BalanceResponse getBalance(Long accountId);
}