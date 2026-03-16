package com.habibullahdm.auth.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    GENERAL_ERROR("GNR-999", "General error"),
    ROLES_NOT_FOUND("AUT-001", "Roles not found"),
    USERNAME_ALREADY_EXISTS("AUT-002", "Username already exists"),
    EMAIL_ALREADY_EXISTS("AUT-003", "Email already exists"),
    USER_NOT_FOUND("AUT-004", "User not found"),
    USER_NOT_ACTIVE("AUT-005", "User not active"),
    INVALID_USERNAME_OR_PASSWORD("AUT-006", "Invalid username or password"),
    UNAUTHORIZED("AUT-007", "Unauthorized"),
    INVALID_TOKEN("AUT-008", "Invalid token");

    private final String code;
    private final String message;
}
