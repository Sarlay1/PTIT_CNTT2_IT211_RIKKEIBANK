package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.KycProfile;
import com.example.rikkeibank.entity.KycStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KycProfileRepository extends JpaRepository<KycProfile, Long> {

    Optional<KycProfile> findByUserId(Long userId);
    Page<KycProfile> findByStatus(KycStatus status, Pageable pageable);
    boolean existsByUserId(Long userId);
}