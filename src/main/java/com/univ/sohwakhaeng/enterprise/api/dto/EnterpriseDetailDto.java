package com.univ.sohwakhaeng.enterprise.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.product.api.dto.ProductResponseDto;
import java.util.List;

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

    public static EnterpriseDetailDto fromEntity(Enterprise enterprise) {
        return new EnterpriseDetailDto(
                enterprise.getId(),
                enterprise.getImageUrl(),
                enterprise.getName(),
                enterprise.getCategory(),
                enterprise.getDescription(),
                enterprise.getStar(),
                enterprise.getLatitude(),
                enterprise.getLongitude(),
                enterprise.getAddress(),
                enterprise.getProducts().stream()
                        .map(ProductResponseDto::fromEntity)
                        .toList()
        );
    }

}

