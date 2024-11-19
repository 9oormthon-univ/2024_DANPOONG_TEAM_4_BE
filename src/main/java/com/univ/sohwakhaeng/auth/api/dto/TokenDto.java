package com.univ.sohwakhaeng.auth.api.dto;

import lombok.Builder;

@Builder
public record TokenDto(String grantType, String accessToken) {}