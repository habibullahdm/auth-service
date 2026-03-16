package com.habibullahdm.auth.controller;

import com.habibullahdm.auth.model.dto.response.GetRolesResponse;
import com.habibullahdm.auth.service.GetRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role/v1")
@RequiredArgsConstructor
public class RoleController {

    private final GetRolesService getRolesService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public GetRolesResponse getRoles() {
        return getRolesService.execute();
    }
}
