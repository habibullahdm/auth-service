package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.dto.request.RegisterRequest;
import com.habibullahdm.auth.model.dto.response.RegisterResponse;
import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    User newUser;
    RegisterRequest registerRequest;
    RegisterResponse registerResponse;

    @BeforeEach
    void init() {
        newUser = User.builder()
                .id("xyz")
                .email("test-email@mail.com")
                .password("test-password")
                .build();
        registerRequest = RegisterRequest.builder()
                .firstName("test")
                .lastName("name")
                .email("test-email@mail.com")
                .password("test-password")
                .build();
        registerResponse = RegisterResponse.builder()
                .id("xyz")
                .email("test-email@mail.com")
                .build();
    }

    @Test
    @DisplayName("Success Register")
    void testSuccessRegister() {
        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerRequest.password())).thenReturn("test-password");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        var result = userService.register(registerRequest);

        assertEquals(registerResponse, result);
    }

    @Test
    @DisplayName("Failure Register - Email Already Exists")
    void testFailureRegisterEmailAlreadyExists() {
        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.of(newUser));

        assertThrows(IllegalArgumentException.class, () -> userService.register(registerRequest));
    }
}
