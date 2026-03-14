package com.habibullahdm.auth.controller;

import com.habibullahdm.auth.model.dto.response.RoleResponse;
import com.habibullahdm.auth.service.GetRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role/v1")
@RequiredArgsConstructor
public class RoleController {

    private final GetRolesService getRolesService;

    @GetMapping
    public RoleResponse getRoles() {
        return getRolesService.execute();
    }
}
