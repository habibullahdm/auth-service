package com.habibullahdm.auth.controller;

import com.habibullahdm.auth.model.dto.request.AuthLoginRequest;
import com.habibullahdm.auth.model.dto.response.AuthLoginResponse;
import com.habibullahdm.auth.model.dto.response.AuthMeResponse;
import com.habibullahdm.auth.service.AuthLoginService;
import com.habibullahdm.auth.service.AuthMeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthLoginService authLoginService;
    private final AuthMeService authMeService;

    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody @Valid AuthLoginRequest request) {
        return authLoginService.execute(request);
    }

    @GetMapping("/me")
    public AuthMeResponse me() {
        return authMeService.execute();
    }
}
