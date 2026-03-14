package com.habibullahdm.auth.exception;

import com.habibullahdm.auth.model.dto.Response;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.ZonedDateTime;

@RecordBuilder
public record ErrorResponse(
        String errorCode,
        String errorMessage,
        ZonedDateTime timestamp
) implements Response {
}
