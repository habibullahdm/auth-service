package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.request.CreateUserRequest;
import com.habibullahdm.auth.model.dto.response.CreateUserResponse;
import com.habibullahdm.auth.model.dto.response.CreateUserResponseBuilder;
import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.model.entity.UserRole;
import com.habibullahdm.auth.model.entity.UserRoleId;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.repository.UserRepository;
import com.habibullahdm.auth.repository.UserRoleRepository;
import com.habibullahdm.auth.utils.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponse execute(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new ServiceException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new ServiceException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .isActive(true)
                .build();

        userRepository.persist(user);

        var roles = roleRepository.findAllById(request.roleIds());

        var userRoles = roles.stream()
                .map(role -> UserRole.builder()
                        .id(
                                UserRoleId.builder()
                                        .userId(user.getId())
                                        .roleId(role.getId())
                                        .build())
                        .build()
                )
                .toList();

        userRoleRepository.persistAll(userRoles);

        return CreateUserResponseBuilder.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
