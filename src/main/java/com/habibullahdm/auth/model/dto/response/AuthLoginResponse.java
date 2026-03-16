package com.habibullahdm.auth.model.dto.response;

import com.habibullahdm.auth.model.dto.impl.BaseProtectedResponse;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record AuthLoginResponse(
        String accessToken,
        String username,
        List<String> roles
) implements BaseProtectedResponse {
}
