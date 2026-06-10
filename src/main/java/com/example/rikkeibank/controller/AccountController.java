package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.BalanceResponse;
import com.example.rikkeibank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}/balance")
    public BalanceResponse getBalance(
            @PathVariable Long id
    ) {

        return accountService.getBalance(id);
    }
}