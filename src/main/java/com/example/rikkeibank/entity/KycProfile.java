package com.example.rikkeibank.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String documentUrl;

    @Enumerated(EnumType.STRING)
    private KycStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}