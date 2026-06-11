package com.example.rikkeibank.entity;

public enum UserStatus {
    PENDING,    // Chờ duyệt KYC
    ACTIVE,     // Đã kích hoạt
    LOCKED,     // Bị khóa
    REJECTED    // KYC bị từ chối
}