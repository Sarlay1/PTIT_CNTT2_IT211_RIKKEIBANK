package com.example.rikkeibank.entity;

import com.example.rikkeibank.enums.KycStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frontImageUrl;

    private String backImageUrl;

    private String selfieUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = KycStatus.PENDING;
        }
    }
}