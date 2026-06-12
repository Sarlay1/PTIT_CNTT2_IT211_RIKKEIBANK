package com.example.rikkeibank.service;

import com.example.rikkeibank.dto.request.KycUploadRequest;
import com.example.rikkeibank.dto.respone.KycResponse;

import java.util.List;

// KycService
public interface KycService {
    KycResponse upload(KycUploadRequest request);
    List<KycResponse> getPendingProfiles();
    void approve(Long id);
    void reject(Long id);
}