package com.example.rikkeibank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionRef; // Mã giao dịch unique

    @Enumerated(EnumType.STRING)
    private TransactionType type; // INTERNAL / INTERBANK

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 3)
    @Builder.Default
    private String currency = "VND";

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TransactionStatus status = TransactionStatus.PENDING;

    // Tài khoản nguồn (null nếu nạp tiền từ ngoài)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    // Tài khoản đích (null nếu rút tiền)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    // Dành cho liên ngân hàng (mock)
    private String destinationBankCode;
    private String destinationAccountNumber;
    private String destinationAccountName;

    @Column(precision = 19, scale = 2)
    @Builder.Default
    private BigDecimal fee = BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    private String failureReason;
}