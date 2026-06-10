package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.KycResponse;
import com.example.rikkeibank.entity.*;
import com.example.rikkeibank.repository.KycProfileRepository;
import com.example.rikkeibank.repository.UserRepository;
import com.example.rikkeibank.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {

    private final UserRepository userRepository;
    private final KycProfileRepository kycRepository;

    @Override
    public KycResponse upload(
            Long userId,
            MultipartFile file
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        KycProfile profile =
                KycProfile.builder()
                        .documentUrl(
                                "https://dummy-storage/"
                                        + file.getOriginalFilename()
                        )
                        .status(KycStatus.PENDING)
                        .user(user)
                        .build();

        kycRepository.save(profile);

        return new KycResponse(
                profile.getId(),
                profile.getStatus().name(),
                profile.getDocumentUrl()
        );
    }
}