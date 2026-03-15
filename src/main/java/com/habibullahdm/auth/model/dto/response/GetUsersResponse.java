package com.habibullahdm.auth.model.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;
import com.habibullahdm.auth.model.dto.impl.BaseProtectedResponse;
import com.habibullahdm.auth.model.dto.impl.Response;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record GetUsersResponse(
        @JsonValue List<User> users
) implements BaseProtectedResponse {
    @RecordBuilder
    public record User(
            String id,
            String username,
            String email,
            Boolean isActive,
            List<String> roles
    ) implements Response {
    }
}
