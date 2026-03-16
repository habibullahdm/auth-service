package com.habibullahdm.auth.model.dto.request;

import com.habibullahdm.auth.model.dto.impl.BaseProtectedRequest;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotBlank;

@RecordBuilder
public record AuthLoginRequest(
        @NotBlank String username,
        @NotBlank String password
) implements BaseProtectedRequest {
}
