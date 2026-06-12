package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.request.TransferRequest;
import com.example.rikkeibank.dto.respone.ApiResponse;
import com.example.rikkeibank.dto.respone.TransactionResponse;
import com.example.rikkeibank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ApiResponse<String> transfer(
            @Valid @RequestBody TransferRequest request) {

        transactionService.transfer(request);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Transfer successful")
                .data("Transaction completed")
                .build();
    }

    @GetMapping("/statement/{accountNumber}")
    public ApiResponse<Page<TransactionResponse>> getStatement(
            @PathVariable String accountNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.<Page<TransactionResponse>>builder()
                .success(true)
                .message("Transaction history")
                .data(
                        transactionService.getStatement(
                                accountNumber,
                                PageRequest.of(page, size)
                        )
                )
                .build();
    }
}