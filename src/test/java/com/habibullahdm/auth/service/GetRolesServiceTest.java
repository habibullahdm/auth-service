package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.entity.Role;
import com.habibullahdm.auth.repository.RoleRepository;
import com.habibullahdm.auth.utils.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetRolesServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private GetRolesService service;

    @Test
    void execute_shouldReturnRoles_whenRolesExist() {
        var role1 = Role.builder()
                .id("role_superadmin")
                .name("SUPER_ADMIN")
                .build();

        var role2 = Role.builder()
                .id("role_admin")
                .name("ADMIN")
                .build();

        Mockito.when(roleRepository.findAll()).thenReturn(List.of(role1, role2));

        var response = service.execute();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.roles().size());

        Assertions.assertEquals("role_superadmin", response.roles().get(0).id());
        Assertions.assertEquals("SUPER_ADMIN", response.roles().get(0).name());

        Mockito.verify(roleRepository).findAll();
    }

    @Test
    void execute_shouldThrowException_whenRolesEmpty() {

        Mockito.when(roleRepository.findAll()).thenReturn(List.of());

        var exception = Assertions.assertThrows(ServiceException.class, () -> service.execute());

        Assertions.assertEquals(ErrorCode.ROLES_NOT_FOUND, exception.getErrorCode());

        Mockito.verify(roleRepository).findAll();
    }
}
