package com.example.rikkeibank.repository;

import com.example.rikkeibank.dto.UserResponse;
import com.example.rikkeibank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
            SELECT new com.example.rikkeibank.dto.UserResponse(
                u.id,
                u.fullName,
                u.email,
                u.phone,
                u.role
            )
            FROM User u
            """)
    Page<UserResponse> findAllUsers(
            Pageable pageable
    );
}