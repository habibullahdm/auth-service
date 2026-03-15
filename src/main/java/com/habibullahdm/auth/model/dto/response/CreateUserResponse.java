package com.habibullahdm.auth.model.dto.response;

import com.habibullahdm.auth.model.dto.impl.BaseProtectedResponse;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record CreateUserResponse(
        String id,
        String username,
        String email
) implements BaseProtectedResponse {
}
