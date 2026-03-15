package com.habibullahdm.auth.model.dto.request;

import com.habibullahdm.auth.model.dto.impl.BaseProtectedRequest;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record CreateUserRequest(
        String username,
        String email,
        String password,
        List<String> roleIds
) implements BaseProtectedRequest {
}
