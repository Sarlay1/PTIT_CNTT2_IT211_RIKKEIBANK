package com.example.rikkeibank.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.rikkeibank.service.CloudStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudStorageServiceImpl
        implements CloudStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) {

        try {

            Map<?, ?> result =
                    cloudinary.uploader().upload(
                            file.getBytes(),
                            ObjectUtils.emptyMap()
                    );

            return result.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Upload file failed"
            );
        }
    }
}