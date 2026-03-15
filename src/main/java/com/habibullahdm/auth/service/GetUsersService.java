package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.dto.response.GetUsersResponse;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseUserBuilder;
import com.habibullahdm.auth.model.projection.UserRoleProjection;
import com.habibullahdm.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUsersService {

    private final UserRepository userRepository;

    public GetUsersResponse execute() {
        var rows = userRepository.findUsersWithRoles();

        var grouped = rows.stream()
                .collect(Collectors.groupingBy(UserRoleProjection::getUserId));

        var users = grouped.values().stream()
                .map(list -> {
                    var first = list.get(0);

                    var roles = list.stream()
                            .map(UserRoleProjection::getRoleName)
                            .filter(Objects::nonNull)
                            .toList();

                    return GetUsersResponseUserBuilder.builder()
                            .id(first.getUserId())
                            .username(first.getUsername())
                            .email(first.getEmail())
                            .isActive(first.getIsActive())
                            .roles(roles)
                            .build();
                })
                .toList();

        return GetUsersResponseBuilder.builder()
                .users(users)
                .build();
    }
}
