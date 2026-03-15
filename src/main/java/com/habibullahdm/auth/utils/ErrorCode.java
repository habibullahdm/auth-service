package com.habibullahdm.auth.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    GENERAL_ERROR("GNR-999", "General error"),
    ROLES_NOT_FOUND("AUT-001", "Roles not found"),
    USERNAME_ALREADY_EXISTS("AUT-002", "Username already exists"),
    EMAIL_ALREADY_EXISTS("AUT-003", "Email already exists");

    private final String code;
    private final String message;
}
