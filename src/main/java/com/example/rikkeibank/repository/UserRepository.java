package com.example.rikkeibank.repository;

import com.example.rikkeibank.entity.Role;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.entity.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByNationalId(String nationalId);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByNationalId(String nationalId);

    @Query("SELECT u FROM User u WHERE " +
           "(:keyword IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:status IS NULL OR u.status = :status) " +
           "AND (:role IS NULL OR u.role = :role)")
    Page<User> searchUsers(@Param("keyword") String keyword,
                           @Param("status") UserStatus status,
                           @Param("role") Role role,
                           Pageable pageable);
}