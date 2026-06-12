package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.respone.AccountResponse;

public interface AccountService {

    AccountResponse getBalance(String accountNumber);
}