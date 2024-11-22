package com.univ.sohwakhaeng.item.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.Item;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 요청 DTO")
public record ItemRequestDto(

        @Schema(description = "상품 ID", example = "1")
        @JsonProperty("product_id")
        Long productId,

        @Schema(description = "상품 수량", example = "5")
        @JsonProperty("quantity")
        int quantity
) {
        public static ItemRequestDto fromEntity(Item item) {
                return new ItemRequestDto(
                        item.getProduct().getId(),
                        item.getQuantity()
                );
        }
}
