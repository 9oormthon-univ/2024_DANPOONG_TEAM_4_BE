package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ContractsInfoDto(
        @JsonProperty
        long enterpriseId,
        @JsonProperty
        long contractId,
        @JsonProperty
        String profileImage,
        @JsonProperty
        String enterpriseName,
        @JsonProperty
        String category
) {
}