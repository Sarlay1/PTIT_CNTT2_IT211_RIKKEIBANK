package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.TransferRequest;
import com.example.rikkeibank.dto.respone.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// TransactionService
public interface TransactionService {
    void transfer(TransferRequest request);
    Page<TransactionResponse> getStatement(
            String accountNumber,
            Pageable pageable);
}