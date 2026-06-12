package com.example.rikkeibank.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycUploadRequest {

    private MultipartFile frontImage;

    private MultipartFile backImage;

    private MultipartFile selfieImage;
}