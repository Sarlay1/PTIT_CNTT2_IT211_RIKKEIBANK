package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.respone.AccountResponse;
import com.example.rikkeibank.dto.respone.ApiResponse;
import com.example.rikkeibank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}/balance")
    public ApiResponse<AccountResponse> getBalance(
            @PathVariable String accountNumber) {

        return ApiResponse.<AccountResponse>builder()
                .success(true)
                .message("Account balance")
                .data(accountService.getBalance(accountNumber))
                .build();
    }
}