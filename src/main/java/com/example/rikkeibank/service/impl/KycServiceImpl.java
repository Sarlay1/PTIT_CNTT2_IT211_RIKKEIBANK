package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.request.KycUploadRequest;
import com.example.rikkeibank.dto.respone.KycResponse;
import com.example.rikkeibank.entity.KycProfile;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.enums.KycStatus;
import com.example.rikkeibank.exception.ResourceNotFoundException;
import com.example.rikkeibank.repository.KycProfileRepository;
import com.example.rikkeibank.repository.UserRepository;
import com.example.rikkeibank.service.CloudStorageService;
import com.example.rikkeibank.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {

    private final KycProfileRepository kycProfileRepository;
    private final UserRepository userRepository;
    private final CloudStorageService cloudStorageService;

    @Override
    public KycResponse upload(KycUploadRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        KycProfile profile = KycProfile.builder()
                .frontImageUrl(
                        cloudStorageService.upload(
                                request.getFrontImage()))
                .backImageUrl(
                        cloudStorageService.upload(
                                request.getBackImage()))
                .selfieUrl(
                        cloudStorageService.upload(
                                request.getSelfieImage()))
                .status(KycStatus.PENDING)
                .user(user)
                .build();

        kycProfileRepository.save(profile);

        return KycResponse.builder()
                .id(profile.getId())
                .frontImageUrl(profile.getFrontImageUrl())
                .backImageUrl(profile.getBackImageUrl())
                .selfieUrl(profile.getSelfieUrl())
                .status(profile.getStatus())
                .build();
    }

    @Override
    public List<KycResponse> getPendingProfiles() {

        return kycProfileRepository.findByStatus(KycStatus.PENDING)
                .stream()
                .map(profile ->
                        KycResponse.builder()
                                .id(profile.getId())
                                .frontImageUrl(profile.getFrontImageUrl())
                                .backImageUrl(profile.getBackImageUrl())
                                .selfieUrl(profile.getSelfieUrl())
                                .status(profile.getStatus())
                                .build())
                .toList();
    }

    @Override
    public void approve(Long id) {

        KycProfile profile = kycProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found"));

        profile.setStatus(KycStatus.CONFIRM);
        profile.setApprovedAt(LocalDateTime.now());

        User user = profile.getUser();
        user.setIsKyc(true);

        userRepository.save(user);
        kycProfileRepository.save(profile);
    }

    @Override
    public void reject(Long id) {

        KycProfile profile = kycProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found"));

        profile.setStatus(KycStatus.REJECT);

        kycProfileRepository.save(profile);
    }
}