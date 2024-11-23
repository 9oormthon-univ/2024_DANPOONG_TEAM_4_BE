package com.univ.sohwakhaeng.item.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.Item;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item Response DTO")
public record ItemResponseDto(

        @Schema(description = "상품의 ID", example = "1")
        @JsonProperty("product_id")
        Long productId,

        @Schema(description = "상품의 이름", example = "유기농 쌀")
        @JsonProperty("product_name")
        String productName,

        @Schema(description = "상품의 단위", example = "1kg")
        @JsonProperty("unit")
        String unit,

        @Schema(description = "상품 이미지 URL", example = "https://example.com/product-image.jpg")
        @JsonProperty("imageUrl")
        String imageUrl,

        @Schema(description = "상품의 수량", example = "10")
        @JsonProperty("quantity")
        int quantity
) {
    public static ItemResponseDto fromEntity(Item item, String itemImageUrl) {
        return new ItemResponseDto(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getUnit(),
                itemImageUrl,
                item.getQuantity()
        );
    }
}
