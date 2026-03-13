package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.dto.request.RegisterRequest;
import com.habibullahdm.auth.model.dto.response.RegisterResponse;
import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public RegisterResponse register(RegisterRequest request) {
        var email = userRepository.findByEmail(request.email());
        if (email.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        var newUser = userRepository.save(User.builder()
                .email(request.email())
                .password(request.password())
                .build());

        return RegisterResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .build();
    }
}
