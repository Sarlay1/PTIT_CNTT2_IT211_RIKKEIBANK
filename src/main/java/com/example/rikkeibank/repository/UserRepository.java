package com.example.rikkeibank.repository;

import com.example.rikkeibank.dto.respone.UserResponse;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<User> findByRole(Role role, Pageable pageable);

    @Query("""
            SELECT new com.example.rikkeibank.dto.respone.UserResponse(
                u.id,
                u.username,
                u.fullName,
                u.email,
                u.role,
                u.isKyc,
                u.enabled
            )
            FROM User u
            """)
    Page<UserResponse> findAllUsers(Pageable pageable);
}