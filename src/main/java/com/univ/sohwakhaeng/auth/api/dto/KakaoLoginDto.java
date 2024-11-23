package com.univ.sohwakhaeng.auth.api.dto;

public record KakaoLoginDto(
        String providerId,
        String nickname,
        String email,
        String profileUrl

) {}
