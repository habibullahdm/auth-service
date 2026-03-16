package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.request.AuthLoginRequest;
import com.habibullahdm.auth.model.dto.response.AuthLoginResponse;
import com.habibullahdm.auth.model.dto.response.AuthLoginResponseBuilder;
import com.habibullahdm.auth.model.projection.AuthUserProjection;
import com.habibullahdm.auth.repository.UserRepository;
import com.habibullahdm.auth.security.JwtService;
import com.habibullahdm.auth.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthLoginResponse execute(AuthLoginRequest request) {
        var rows = userRepository.findAuthUserByUsername(request.username());

        if (rows.isEmpty()) {
            throw new ServiceException(ErrorCode.USER_NOT_FOUND);
        }

        var first = rows.get(0);

        if (!first.getIsActive()) {
            throw new ServiceException(ErrorCode.USER_NOT_ACTIVE);
        }

        var isPasswordValid = passwordEncoder.matches(
                request.password(),
                first.getPassword()
        );

        if (!isPasswordValid) {
            throw new ServiceException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        var roles = rows.stream()
                .map(AuthUserProjection::getRoleName)
                .filter(Objects::nonNull)
                .toList();

        var token = jwtService.generateToken(
                first.getUserId(),
                first.getUsername(),
                roles
        );

        return AuthLoginResponseBuilder.builder()
                .accessToken(token)
                .username(first.getUsername())
                .roles(roles)
                .build();
    }
}
