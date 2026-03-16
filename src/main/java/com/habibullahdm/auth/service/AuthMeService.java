package com.habibullahdm.auth.service;

import com.habibullahdm.auth.exception.ServiceException;
import com.habibullahdm.auth.model.dto.response.AuthMeResponse;
import com.habibullahdm.auth.model.dto.response.AuthMeResponseBuilder;
import com.habibullahdm.auth.security.UserPrincipal;
import com.habibullahdm.auth.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthMeService {

    public AuthMeResponse execute() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal principal)) {
            throw new ServiceException(ErrorCode.UNAUTHORIZED);
        }

        return AuthMeResponseBuilder.builder()
                .id(principal.getId())
                .username(principal.getUsername())
                .roles(principal.getRoles())
                .build();
    }
}
