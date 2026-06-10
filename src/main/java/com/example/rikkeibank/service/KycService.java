package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.KycResponse;
import org.springframework.web.multipart.MultipartFile;

public interface KycService {

    KycResponse upload(
            Long userId,
            MultipartFile file
    );
}