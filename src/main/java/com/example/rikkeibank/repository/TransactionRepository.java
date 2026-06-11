package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.Transaction;
import com.example.rikkeibank.entity.TransactionStatus;
import com.example.rikkeibank.entity.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionRef(String transactionRef);

    @Query("SELECT t FROM Transaction t WHERE " +
           "(t.sourceAccount.id = :accountId OR t.destinationAccount.id = :accountId) " +
           "AND (:type IS NULL OR t.type = :type) " +
           "AND (:status IS NULL OR t.status = :status) " +
           "AND (:from IS NULL OR t.createdAt >= :from) " +
           "AND (:to IS NULL OR t.createdAt <= :to) " +
           "ORDER BY t.createdAt DESC")
    Page<Transaction> findByAccountId(@Param("accountId") Long accountId,
                                      @Param("type") TransactionType type,
                                      @Param("status") TransactionStatus status,
                                      @Param("from") LocalDateTime from,
                                      @Param("to") LocalDateTime to,
                                      Pageable pageable);
}