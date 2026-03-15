package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.entity.Role;
import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.model.entity.UserRole;
import com.habibullahdm.auth.model.entity.UserRoleId;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GetUsersServiceTest {

    @InjectMocks
    GetUsersService getUsersService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserRoleRepository userRoleRepository;

    @Mock
    RoleRepository roleRepository;

    @Test
    void execute_shouldReturnUsersWithRoles() {
        var user = User.builder()
                .id("usr_1")
                .username("admin")
                .email("admin@mail.com")
                .isActive(true)
                .build();

        var role = Role.builder()
                .id("role_admin")
                .name("ADMIN")
                .build();

        var userRole = UserRole.builder()
                .id(
                        UserRoleId.builder()
                                .userId("usr_1")
                                .roleId("role_admin")
                                .build()
                )
                .build();

        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(user));

        Mockito.when(userRoleRepository.findByIdUserId("usr_1"))
                .thenReturn(List.of(userRole));

        Mockito.when(roleRepository.findById("role_admin"))
                .thenReturn(Optional.of(role));

        var response = getUsersService.execute();

        Assertions.assertEquals(1, response.users().size());
        Assertions.assertEquals("usr_1", response.users().get(0).id());
        Assertions.assertEquals("admin", response.users().get(0).username());
        Assertions.assertEquals("ADMIN", response.users().get(0).roles().get(0));
    }

    @Test
    void execute_shouldReturnUserWithoutRoles() {
        var user = User.builder()
                .id("usr_1")
                .username("admin")
                .email("admin@mail.com")
                .isActive(true)
                .build();

        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(user));

        Mockito.when(userRoleRepository.findByIdUserId("usr_1"))
                .thenReturn(List.of());

        var response = getUsersService.execute();

        Assertions.assertEquals(1, response.users().size());
        Assertions.assertTrue(response.users().get(0).roles().isEmpty());
    }

    @Test
    void execute_shouldReturnEmptyList_whenNoUsers() {

        Mockito.when(userRepository.findAll())
                .thenReturn(List.of());

        var response = getUsersService.execute();

        Assertions.assertTrue(response.users().isEmpty());
    }
}
