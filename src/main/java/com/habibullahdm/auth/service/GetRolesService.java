package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.response.RoleResponse;
import com.habibullahdm.auth.model.dto.response.RoleResponseBuilder;
import com.habibullahdm.auth.model.dto.response.RoleResponseRoleBuilder;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRolesService {
    private final RoleRepository roleRepository;

    public RoleResponse execute() {
        var roles = roleRepository.findAll()
            .stream()
            .map(role -> RoleResponseRoleBuilder.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .build()
            )
            .toList();
        if (roles.isEmpty()) {
            throw new ServiceException(ErrorCode.ROLES_NOT_FOUND);
        }

        return RoleResponseBuilder.builder()
            .roles(roles)
            .build();
    }
}
