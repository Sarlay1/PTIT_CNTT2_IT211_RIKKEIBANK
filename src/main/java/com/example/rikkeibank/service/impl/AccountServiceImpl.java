package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.BalanceResponse;
import com.example.rikkeibank.entity.Account;
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
    public BalanceResponse getBalance(Long accountId) {

        Account account =
                accountRepository.findById(accountId)
                        .orElseThrow();

        return new BalanceResponse(
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}