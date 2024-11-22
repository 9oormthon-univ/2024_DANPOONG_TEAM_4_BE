package com.univ.sohwakhaeng.enterprise.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.api.dto.ProductResponseDto;
import java.util.List;
import java.util.function.Function;

public record EnterpriseDetailDto(
        @JsonProperty("enterprise_id")
        Long id,

        @JsonProperty("enterprise_image_url")
        String imageUrl,

        @JsonProperty("enterprise_name")
        String name,

        @JsonProperty("category")
        Category category,

        @JsonProperty("description")
        String description,

        @JsonProperty("star")
        Double star,

        @JsonProperty("latitude")
        Double latitude,

        @JsonProperty("longitude")
        Double longitude,

        @JsonProperty("address")
        String address,

        @JsonProperty("products")
        List<ProductResponseDto> products
) {

    public static EnterpriseDetailDto fromEntity(Enterprise enterprise, String imageUrl, Function<Product, String> getProductImageUrl) {
        return new EnterpriseDetailDto(
                enterprise.getId(),
                imageUrl,
                enterprise.getName(),
                enterprise.getCategory(),
                enterprise.getDescription(),
                enterprise.getStar(),
                enterprise.getLatitude(),
                enterprise.getLongitude(),
                enterprise.getAddress(),
                enterprise.getProducts().stream()
                        .map(product -> {
                            String productImageUrl = getProductImageUrl.apply(product);
                            return ProductResponseDto.fromEntity(product, productImageUrl);
                        })
                        .toList()
        );
    }

}

