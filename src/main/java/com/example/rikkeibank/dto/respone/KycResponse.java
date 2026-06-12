package com.example.rikkeibank.dto.respone;

import com.example.rikkeibank.enums.KycStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycResponse {

    private Long id;

    private String frontImageUrl;

    private String backImageUrl;

    private String selfieUrl;

    private KycStatus status;
}