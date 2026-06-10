package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.KycProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycProfileRepository
        extends JpaRepository<KycProfile, Long> {
}