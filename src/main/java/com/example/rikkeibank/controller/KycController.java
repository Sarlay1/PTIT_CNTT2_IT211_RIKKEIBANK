package com.example.rikkeibank.controller;

import com.example.rikkeibank.dto.request.KycUploadRequest;
import com.example.rikkeibank.dto.respone.ApiResponse;
import com.example.rikkeibank.dto.respone.KycResponse;
import com.example.rikkeibank.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;

    @PostMapping("/upload")
    public ApiResponse<KycResponse> upload(
            @RequestParam MultipartFile frontImage,
            @RequestParam MultipartFile backImage,
            @RequestParam MultipartFile selfieImage) {

        KycUploadRequest request =
                KycUploadRequest.builder()
                        .frontImage(frontImage)
                        .backImage(backImage)
                        .selfieImage(selfieImage)
                        .build();

        return ApiResponse.<KycResponse>builder()
                .success(true)
                .message("Upload successful")
                .data(kycService.upload(request))
                .build();
    }

    @GetMapping("/pending")
    public ApiResponse<List<KycResponse>> getPendingProfiles() {

        return ApiResponse.<List<KycResponse>>builder()
                .success(true)
                .message("Pending KYC profiles")
                .data(kycService.getPendingProfiles())
                .build();
    }

    @PutMapping("/{id}/approve")
    public ApiResponse<String> approve(
            @PathVariable Long id) {

        kycService.approve(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("KYC approved")
                .data("Approved")
                .build();
    }

    @PutMapping("/{id}/reject")
    public ApiResponse<String> reject(
            @PathVariable Long id) {

        kycService.reject(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("KYC rejected")
                .data("Rejected")
                .build();
    }
}