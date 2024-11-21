package com.univ.sohwakhaeng.contract.api.dto;

import lombok.Builder;

@Builder
public record ContractsInfoDto(
        long enterpriseId,
        long contractId,
        String profileImage,
        String enterpriseName,
        String category
) {
}