package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "계약 정보 DTO")
@Builder
public record ContractsInfoDto(

        @Schema(description = "계약과 연관된 기업 ID", example = "1")
        @JsonProperty
        long enterpriseId,

        @Schema(description = "계약 ID", example = "101")
        @JsonProperty
        long contractId,

        @Schema(description = "기업 프로필 이미지 URL", example = "https://example.com/profile-image.jpg")
        @JsonProperty
        String profileImage,

        @Schema(description = "기업 이름", example = "초록믿음강진")
        @JsonProperty
        String enterpriseName,

        @Schema(description = "기업 카테고리", example = "AGRICULTURAL_PRODUCTS")
        @JsonProperty
        String category
) {
}
