package com.univ.sohwakhaeng.contract.api.dto;

public record ProductDto(
        long productId,
        String productName,
        int quantity
) {}