package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Schema(description = "계약 상품 응답 DTO")
public record ContractProducsResDto(

        @Schema(description = "상품 이름", example = "유기농 쌀")
        @JsonProperty
        String name,

        @Schema(description = "상품 수량", example = "10")
        @JsonProperty
        int quantity
) {
}
