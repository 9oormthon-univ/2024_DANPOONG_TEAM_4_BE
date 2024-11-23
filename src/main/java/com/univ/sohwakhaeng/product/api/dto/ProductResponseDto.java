package com.univ.sohwakhaeng.product.api.dto;

import com.univ.sohwakhaeng.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product Response DTO")
public record ProductResponseDto(

        @Schema(description = "상품의 ID", example = "1")
        @JsonProperty("product_id")
        Long id,

        @Schema(description = "기업의 ID", example = "101")
        @JsonProperty("enterprise_id")
        Long enterpriseId,

        @Schema(description = "상품의 이름", example = "유기농 쌀")
        @JsonProperty("product_name")
        String name,

        @Schema(description = "상품의 단위", example = "1kg")
        @JsonProperty("unit")
        String unit,

        @Schema(description = "상품 이미지 URL", example = "https://example.com/product-image.jpg")
        @JsonProperty("imageUrl")
        String imageUrl,

        @Schema(description = "상품의 가격", example = "5000")
        @JsonProperty("product_price")
        Integer price
) {

    public static ProductResponseDto fromEntity(Product product, String imageUrl) {
        return new ProductResponseDto(
                product.getId(),
                product.getEnterprise() != null ? product.getEnterprise().getId() : null,
                product.getName(),
                product.getUnit(),
                imageUrl,
                product.getPrice()
        );
    }

}
