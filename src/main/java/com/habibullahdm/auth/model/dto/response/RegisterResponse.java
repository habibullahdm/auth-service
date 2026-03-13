package com.habibullahdm.auth.model.dto.response;

import lombok.Builder;

@Builder
public record RegisterResponse(
        String id,
        String email
) { }
