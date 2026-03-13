package com.habibullahdm.auth.controller;


import com.habibullahdm.auth.model.dto.BaseDataResponse;
import com.habibullahdm.auth.model.dto.request.RegisterRequest;
import com.habibullahdm.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> getList(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(BaseDataResponse.builder()
                .data(userService.register(request))
                .build());
    }

}
