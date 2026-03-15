package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.dto.response.GetUsersResponse;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseUserBuilder;
import com.habibullahdm.auth.model.entity.Role;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.repository.UserRepository;
import com.habibullahdm.auth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUsersService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public GetUsersResponse execute() {
        var users = userRepository.findAll();

        var response = users.stream()
                .map(user -> {
                    var roles = userRoleRepository
                            .findByIdUserId(user.getId())
                            .stream()
                            .map(userRole -> roleRepository
                                    .findById(userRole.getId().getRoleId())
                                    .map(Role::getName)
                                    .orElse(null)
                            )
                            .toList();

                    return GetUsersResponseUserBuilder.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .isActive(user.isActive())
                            .roles(roles)
                            .build();
                })
                .toList();

        return GetUsersResponseBuilder.builder()
                .users(response)
                .build();
    }
}
