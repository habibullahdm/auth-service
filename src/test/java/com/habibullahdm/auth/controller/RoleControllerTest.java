package com.habibullahdm.auth.controller;

import com.habibullahdm.auth.model.dto.response.GetRolesResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetRolesResponseRoleBuilder;
import com.habibullahdm.auth.service.GetRolesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @InjectMocks
    RoleController roleController;

    @Mock
    GetRolesService getRolesService;

    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    void getRoles_shouldReturnRoles() throws Exception {
        var role1 = GetRolesResponseRoleBuilder.builder()
                        .id("role_superadmin")
                        .name("SUPER_ADMIN")
                        .build();

        var role2 = GetRolesResponseRoleBuilder.builder()
                        .id("role_admin")
                        .name("ADMIN")
                        .build();

        var response = GetRolesResponseBuilder.builder()
                        .roles(List.of(role1, role2))
                        .build();

        Mockito.when(getRolesService.execute()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/role/v1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("role_superadmin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("SUPER_ADMIN"));

        Mockito.verify(getRolesService).execute();
    }
}
