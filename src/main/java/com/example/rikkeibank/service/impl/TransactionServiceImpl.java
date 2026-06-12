package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.request.TransferRequest;
import com.example.rikkeibank.dto.respone.TransactionResponse;
import com.example.rikkeibank.entity.Account;
import com.example.rikkeibank.entity.Transaction;
import com.example.rikkeibank.exception.InsufficientBalanceException;
import com.example.rikkeibank.exception.ResourceNotFoundException;
import com.example.rikkeibank.repository.AccountRepository;
import com.example.rikkeibank.repository.TransactionRepository;
import com.example.rikkeibank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void transfer(TransferRequest request) {

        Account fromAccount = accountRepository
                .findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Source account not found"));

        Account toAccount = accountRepository
                .findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Target account not found"));

        if (fromAccount.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new InsufficientBalanceException(
                    "Insufficient balance");
        }

        fromAccount.setBalance(
                fromAccount.getBalance()
                        .subtract(request.getAmount()));

        toAccount.setBalance(
                toAccount.getBalance()
                        .add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = Transaction.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();

        transactionRepository.save(transaction);
    }

    @Override
    public Page<TransactionResponse> getStatement(
            String accountNumber,
            Pageable pageable) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"));

        Page<Transaction> transactions =
                transactionRepository.findByFromAccountOrToAccount(
                        account,
                        account,
                        pageable);

        List<TransactionResponse> responses =
                transactions.stream()
                        .map(transaction -> {

                            String type =
                                    transaction.getFromAccount()
                                            .getId()
                                            .equals(account.getId())
                                            ? "DEBIT"
                                            : "CREDIT";

                            return TransactionResponse.builder()
                                    .id(transaction.getId())
                                    .fromAccount(
                                            transaction.getFromAccount()
                                                    .getAccountNumber())
                                    .toAccount(
                                            transaction.getToAccount()
                                                    .getAccountNumber())
                                    .amount(transaction.getAmount())
                                    .transactionType(type)
                                    .description(
                                            transaction.getDescription())
                                    .createdAt(
                                            transaction.getCreatedAt())
                                    .build();
                        })
                        .toList();

        return new PageImpl<>(
                responses,
                pageable,
                transactions.getTotalElements()
        );
    }
}