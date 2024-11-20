package com.univ.sohwakhaeng.item.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemRequestDto(
        @JsonProperty("product_id")
        Long productId,

        @JsonProperty("quantity")
        int quantity
) {
}
