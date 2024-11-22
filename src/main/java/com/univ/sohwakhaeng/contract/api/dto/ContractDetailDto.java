package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "계약 상세 정보 DTO")
@Builder
public record ContractDetailDto(

        @Schema(description = "기업 ID", example = "1")
        @JsonProperty
        long enterpriseId,

        @Schema(description = "기업 프로필 이미지 URL", example = "https://example.com/profile-image.jpg")
        @JsonProperty
        String profileImgUrl,

        @Schema(description = "기업 이름", example = "초록믿음강진")
        @JsonProperty
        String enterpriseName,

        @Schema(description = "기업 카테고리", example = "AGRICULTURAL_PRODUCTS")
        @JsonProperty
        String category,

        @Schema(description = "정기 배송 정보", example = "주간 배송 가능")
        @JsonProperty
        String regularDelivery,

        @Schema(description = "계약 요청 기간", example = "3개월")
        @JsonProperty
        String requestTerm,

        @Schema(description = "계약 제품 목록", example = """
                [
                    {
                        "productId": 1,
                        "productName": "유기농 쌀",
                        "unit": "1kg",
                        "price": 5000
                    },
                    {
                        "productId": 2,
                        "productName": "감자",
                        "unit": "2kg",
                        "price": 4000
                    }
                ]
                """)
        @JsonProperty
        List<ContractProducsResDto> products
) {
}
