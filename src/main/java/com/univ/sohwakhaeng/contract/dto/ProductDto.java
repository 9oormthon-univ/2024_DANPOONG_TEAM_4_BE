package com.univ.sohwakhaeng.contract.dto;

public record ProductDto(
        long productId,
        String productName,
        int quantity
) {}