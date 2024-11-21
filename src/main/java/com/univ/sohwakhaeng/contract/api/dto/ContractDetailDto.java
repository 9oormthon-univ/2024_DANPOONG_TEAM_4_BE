package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record ContractDetailDto(
        @JsonProperty
        long enterpriseId,
        @JsonProperty
        String profileImgUrl,
        @JsonProperty
        String enterpriseName,
        @JsonProperty
        String category,
        @JsonProperty
        String reqularDelivery,
        @JsonProperty
        String requestTerm,
        @JsonProperty
        List<ContractProducsResDto> products
) {
}
