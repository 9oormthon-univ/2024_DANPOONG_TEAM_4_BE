package com.univ.sohwakhaeng.product.api.dto;

import com.univ.sohwakhaeng.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponseDto(
        @JsonProperty("product_id")
        Long id,

        @JsonProperty("enterprise_id")
        Long enterpriseId,

        @JsonProperty("product_name")
        String name,

        @JsonProperty("unit")
        String unit,

        @JsonProperty("imageUrl")
        String imageUrl,

        @JsonProperty("product_price")
        Integer price
) {

    public static ProductResponseDto fromEntity(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getEnterprise() != null ? product.getEnterprise().getId() : null,
                product.getName(),
                product.getUnit(),
                product.getImageUrl(),
                product.getPrice()
        );
    }

}
