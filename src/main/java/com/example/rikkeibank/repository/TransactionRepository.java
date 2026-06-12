package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.Account;
import com.example.rikkeibank.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByFromAccountOrToAccount(
            Account fromAccount,
            Account toAccount,
            Pageable pageable
    );
}