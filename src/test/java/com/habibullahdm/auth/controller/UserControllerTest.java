package com.habibullahdm.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habibullahdm.auth.model.dto.request.CreateUserRequestBuilder;
import com.habibullahdm.auth.model.dto.response.CreateUserResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseBuilder;
import com.habibullahdm.auth.model.dto.response.GetUsersResponseUserBuilder;
import com.habibullahdm.auth.service.CreateUserService;
import com.habibullahdm.auth.service.GetUsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    CreateUserService createUserService;

    @Mock
    private GetUsersService getUsersService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        var request = CreateUserRequestBuilder.builder()
                .username("admin")
                .email("admin@mail.com")
                .password("password")
                .roleIds(List.of("role_admin"))
                .build();

        var response = CreateUserResponseBuilder.builder()
                .id("usr_123")
                .username("admin")
                .email("admin@mail.com")
                .build();

        Mockito.when(createUserService.execute(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected.id").value("usr_123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected.username").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected.email").value("admin@mail.com"));
    }

    @Test
    void getUsers_shouldReturnUserList() throws Exception {
        var users = List.of(
                GetUsersResponseUserBuilder.builder()
                        .id("usr_123")
                        .username("admin")
                        .email("admin@mail.com")
                        .isActive(true)
                        .roles(List.of("ADMIN"))
                        .build()
        );

        var response = GetUsersResponseBuilder.builder()
                .users(users)
                .build();

        Mockito.when(getUsersService.execute())
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/v1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected[0].id").value("usr_123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected[0].username").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataProtected[0].email").value("admin@mail.com"));
    }
}
