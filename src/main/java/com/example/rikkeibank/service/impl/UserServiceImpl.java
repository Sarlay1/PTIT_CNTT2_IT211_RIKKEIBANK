package com.example.rikkeibank.service.impl;

import com.example.rikkeibank.dto.request.CreateUserRequest;
import com.example.rikkeibank.dto.request.UpdateUserRequest;
import com.example.rikkeibank.dto.respone.UserResponse;
import com.example.rikkeibank.entity.User;
import com.example.rikkeibank.exception.ResourceNotFoundException;
import com.example.rikkeibank.repository.UserRepository;
import com.example.rikkeibank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAllUsers(pageable);
    }

    @Override
    public UserResponse getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getIsKyc(),
                user.getEnabled()
        );
    }

    @Override
    public UserResponse create(CreateUserRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return getById(user.getId());
    }

    @Override
    public UserResponse update(Long id,
                               UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setEnabled(request.getEnabled());

        userRepository.save(user);

        return getById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}