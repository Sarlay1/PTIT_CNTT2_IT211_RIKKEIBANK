package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.respone.AccountResponse;
import com.example.rikkeibank.entity.Account;
import com.example.rikkeibank.exception.ResourceNotFoundException;
import com.example.rikkeibank.repository.AccountRepository;
import com.example.rikkeibank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
        implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse getBalance(
            String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"));

        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .build();
    }
}