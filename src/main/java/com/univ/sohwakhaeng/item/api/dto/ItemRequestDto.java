package com.univ.sohwakhaeng.item.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.Item;

public record ItemRequestDto(
        @JsonProperty("product_id")
        Long productId,

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
