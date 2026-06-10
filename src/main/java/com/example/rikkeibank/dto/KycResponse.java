package com.example.rikkeibank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KycResponse {

    private Long id;

    private String status;

    private String documentUrl;
}