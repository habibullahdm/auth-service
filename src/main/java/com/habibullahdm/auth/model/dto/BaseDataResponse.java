package com.habibullahdm.auth.model.dto;

import lombok.Builder;

@Builder
public record BaseDataResponse<T>(T data) {
}
