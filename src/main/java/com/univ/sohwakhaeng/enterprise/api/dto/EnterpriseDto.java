package com.univ.sohwakhaeng.enterprise.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.product.api.dto.ProductDto;
import java.util.List;

public record EnterpriseDto(
        @JsonProperty("enterprise_id")
        Long id,

        @JsonProperty("enterprise_image_url")
        String imageUrl,

        @JsonProperty("enterprise_name")
        String name,

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
        List<ProductDto> products
) {

    public static EnterpriseDto fromEntity(Enterprise enterprise) {
        return new EnterpriseDto(
                enterprise.getId(),
                enterprise.getImageUrl(),
                enterprise.getName(),
                enterprise.getDescription(),
                enterprise.getStar(),
                enterprise.getLatitude(),
                enterprise.getLongitude(),
                enterprise.getAddress(),
                enterprise.getProducts().stream()
                        .map(ProductDto::fromEntity)
                        .toList()
        );
    }

}

