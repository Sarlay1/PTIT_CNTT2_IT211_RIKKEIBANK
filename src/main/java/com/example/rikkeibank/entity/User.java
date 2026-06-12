package com.example.rikkeibank.entity;

import com.example.rikkeibank.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean isKyc = false;

    @Column(nullable = false)
    private Boolean enabled = true;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    private KycProfile kycProfile;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}