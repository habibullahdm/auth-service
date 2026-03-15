package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.response.GetRolesResponse;
import com.habibullahdm.auth.model.dto.response.GetRolesResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetRolesResponseRoleBuilder;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRolesService {
    private final RoleRepository roleRepository;

    public GetRolesResponse execute() {
        var roles = roleRepository.findAll()
                .stream()
                .map(role -> GetRolesResponseRoleBuilder.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .build()
                )
                .toList();
        if (roles.isEmpty()) {
            throw new ServiceException(ErrorCode.ROLES_NOT_FOUND);
        }

        return GetRolesResponseBuilder.builder()
                .roles(roles)
                .build();
    }
}
