package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.KycResponse;
import com.example.rikkeibank.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/kyc")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;

    @PostMapping("/upload")
    public KycResponse upload(
            @RequestParam Long userId,
            @RequestParam MultipartFile file
    ) {

        return kycService.upload(
                userId,
                file
        );
    }
}