package com.habibullahdm.auth.service;

import com.habibullahdm.auth.model.projection.UserRoleStub;
import com.habibullahdm.auth.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetUsersServiceTest {

    @InjectMocks
    GetUsersService getUsersService;

    @Mock
    UserRepository userRepository;

    @Test
    void execute_shouldReturnUsersWithRoles() {
        var row1 = new UserRoleStub(
                "usr_1",
                "admin",
                "admin@mail.com",
                true,
                "ADMIN"
        );

        var row2 = new UserRoleStub(
                "usr_1",
                "admin",
                "admin@mail.com",
                true,
                "SUPER_ADMIN"
        );

        Mockito.when(userRepository.findUsersWithRoles())
                .thenReturn(List.of(row1, row2));

        var response = getUsersService.execute();

        Assertions.assertEquals(1, response.users().size());
        Assertions.assertEquals("usr_1", response.users().get(0).id());
        Assertions.assertEquals("admin", response.users().get(0).username());

        Assertions.assertEquals(2, response.users().get(0).roles().size());
        Assertions.assertTrue(response.users().get(0).roles().contains("ADMIN"));
        Assertions.assertTrue(response.users().get(0).roles().contains("SUPER_ADMIN"));
    }

    @Test
    void execute_shouldReturnUserWithoutRoles() {
        var row = new UserRoleStub(
                "usr_1",
                "admin",
                "admin@mail.com",
                true,
                null
        );

        Mockito.when(userRepository.findUsersWithRoles())
                .thenReturn(List.of(row));

        var response = getUsersService.execute();

        Assertions.assertEquals(1, response.users().size());
        Assertions.assertTrue(response.users().get(0).roles().isEmpty());
    }

    @Test
    void execute_shouldReturnEmptyList_whenNoUsers() {
        Mockito.when(userRepository.findUsersWithRoles())
                .thenReturn(List.of());

        var response = getUsersService.execute();

        Assertions.assertTrue(response.users().isEmpty());
    }
}
