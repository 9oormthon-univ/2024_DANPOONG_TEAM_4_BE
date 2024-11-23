package com.univ.sohwakhaeng.auth.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "토큰 DTO")
public record TokenDto(

        @Schema(description = "토큰의 인증 타입", example = "Bearer")
        String grantType,

        @Schema(description = "인증을 위한 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken
) {}
