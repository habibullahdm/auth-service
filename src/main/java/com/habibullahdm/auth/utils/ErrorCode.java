package com.habibullahdm.auth.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    GENERAL_ERROR("GNR-999", "Internal server error"),
    ROLES_NOT_FOUND("AUT-001", "Roles not found");

    private final String code;
    private final String message;
}
