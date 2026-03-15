package com.habibullahdm.auth.model.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;
import com.habibullahdm.auth.model.dto.impl.BaseDataResponse;
import com.habibullahdm.auth.model.dto.impl.Response;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record GetRolesResponse(
        @JsonValue List<Role> roles
) implements BaseDataResponse {
    @RecordBuilder
    public record Role(
            String id,
            String name
    ) implements Response {
    }
}
