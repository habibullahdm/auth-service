package com.habibullahdm.auth.controller;

import com.habibullahdm.auth.model.dto.request.CreateUserRequest;
import com.habibullahdm.auth.model.dto.response.CreateUserResponse;
import com.habibullahdm.auth.model.dto.response.GetUsersResponse;
import com.habibullahdm.auth.service.CreateUserService;
import com.habibullahdm.auth.service.GetUsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/v1")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserService createUserService;
    private final GetUsersService getUsersService;

    @PostMapping
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return createUserService.execute(request);
    }

    @GetMapping
    public GetUsersResponse getUsers() {
        return getUsersService.execute();
    }
}
