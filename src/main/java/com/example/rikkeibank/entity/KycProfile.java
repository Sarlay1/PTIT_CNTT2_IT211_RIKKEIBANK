package com.example.rikkeibank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_profiles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class KycProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentUrl;       // Ảnh CCCD mặt trước
    private String documentBackUrl;   // Ảnh CCCD mặt sau
    private String selfieUrl;         // Ảnh selfie

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private KycStatus status = KycStatus.PENDING;

    private String rejectionReason;   // Lý do từ chối (nếu có)

    private String reviewedBy;        // Staff đã duyệt

    private LocalDateTime reviewedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}