package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.Account;
import com.example.rikkeibank.entity.AccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    // Pessimistic lock để tránh race condition khi chuyển tiền
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumberWithLock(@Param("accountNumber") String accountNumber);

    List<Account> findByUserId(Long userId);
    Page<Account> findByUserId(Long userId, Pageable pageable);

    boolean existsByAccountNumber(String accountNumber);
    long countByStatus(AccountStatus status);
}