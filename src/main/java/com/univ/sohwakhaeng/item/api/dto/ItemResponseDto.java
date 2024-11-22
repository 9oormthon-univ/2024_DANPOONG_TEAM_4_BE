package com.univ.sohwakhaeng.item.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.Item;

public record ItemResponseDto(
        @JsonProperty("product_id")
        Long productId,

        @JsonProperty("product_name")
        String productName,

        @JsonProperty("unit")
        String unit,

        @JsonProperty("imageUrl")
        String imageUrl,

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
