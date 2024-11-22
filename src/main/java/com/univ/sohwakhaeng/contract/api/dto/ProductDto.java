package com.univ.sohwakhaeng.contract.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "제품 DTO")
public record ProductDto(

        @Schema(description = "제품의 ID", example = "1")
        long productId,

        @Schema(description = "제품 이름", example = "유기농 쌀")
        String productName,

        @Schema(description = "제품 수량", example = "10")
        int quantity
) {}
