package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.KycProfile;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.enums.KycStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KycProfileRepository extends JpaRepository<KycProfile, Long> {

    Optional<KycProfile> findByUser(User user);

    List<KycProfile> findByStatus(KycStatus status);
}