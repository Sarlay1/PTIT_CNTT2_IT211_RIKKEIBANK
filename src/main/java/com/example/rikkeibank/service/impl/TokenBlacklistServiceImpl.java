package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl
        implements TokenBlacklistService {

    private final RedisTemplate<String, String>
            redisTemplate;

    @Override
    public void blacklist(String token) {

        redisTemplate.opsForValue()
                .set(token, "BLACKLISTED");
    }

    @Override
    public boolean isBlacklisted(String token) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(token)
        );
    }
}