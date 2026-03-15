package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.request.CreateUserRequest;
import com.habibullahdm.auth.model.dto.request.CreateUserRequestBuilder;
import com.habibullahdm.auth.model.entity.Role;
import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.repository.UserRepository;
import com.habibullahdm.auth.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @InjectMocks
    CreateUserService createUserService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    CreateUserRequest request = CreateUserRequestBuilder.builder()
            .username("admin")
            .email("admin@mail.com")
            .password("password")
            .roleIds(List.of("role_admin"))
            .build();

    @Test
    void execute_shouldCreateUserSuccessfully() {
        var role = Role.builder()
                .id("role_admin")
                .name("ADMIN")
                .build();

        Mockito.when(userRepository.existsByUsername("admin")).thenReturn(false);
        Mockito.when(userRepository.existsByEmail("admin@mail.com")).thenReturn(false);
        Mockito.when(passwordEncoder.encode("password")).thenReturn("encoded_password");
        Mockito.when(roleRepository.findAllById(List.of("role_admin")))
                .thenReturn(List.of(role));

        var response = createUserService.execute(request);

        Assertions.assertEquals("admin", response.username());
        Assertions.assertEquals("admin@mail.com", response.email());

        Mockito.verify(userRepository).save(Mockito.any(User.class));
        Mockito.verify(userRoleRepository).saveAll(Mockito.anyList());
    }

    @Test
    void execute_shouldThrowException_whenUsernameExists() {
        Mockito.when(userRepository.existsByUsername("admin"))
                .thenReturn(true);

        Assertions.assertThrows(
                ServiceException.class,
                () -> createUserService.execute(request)
        );

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void execute_shouldThrowException_whenEmailExists() {
        Mockito.when(userRepository.existsByUsername("admin")).thenReturn(false);
        Mockito.when(userRepository.existsByEmail("admin@mail.com")).thenReturn(true);

        Assertions.assertThrows(
                ServiceException.class,
                () -> createUserService.execute(request)
        );

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}
