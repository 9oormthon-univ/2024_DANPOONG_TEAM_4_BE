package com.univ.sohwakhaeng.contract.dto;

import java.util.List;

public record ContractReqDto(
        long enterpriseId,
        int deliveryWeek,
        String deliveryDay,
        String takeMethod,
        String requestedTerm,
        long totalPrice,
        List<ProductDto> products
) {
}