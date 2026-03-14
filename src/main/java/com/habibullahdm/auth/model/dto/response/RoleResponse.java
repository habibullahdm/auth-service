package com.habibullahdm.auth.model.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;
import com.habibullahdm.auth.model.dto.BaseDataResponse;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record RoleResponse(
        @JsonValue List<Role> roles
) implements BaseDataResponse {
    @RecordBuilder
    public record Role(
            String id,
            String name
    ) {
    }
}
