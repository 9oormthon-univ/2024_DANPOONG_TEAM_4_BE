package com.univ.sohwakhaeng.product.api.dto;

public record ProductRequestDto(
        Long id,
        Long enterpriseId,
        String name,
        String unit,
        Integer price
) {
}
