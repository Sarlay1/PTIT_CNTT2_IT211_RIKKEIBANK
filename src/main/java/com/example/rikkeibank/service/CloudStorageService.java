package com.example.rikkeibank.service;

import org.springframework.web.multipart.MultipartFile;

// CloudStorageService
public interface CloudStorageService {
    String upload(MultipartFile file);
}