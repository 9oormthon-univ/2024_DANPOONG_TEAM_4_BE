package com.univ.sohwakhaeng.contract.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ContractProducsResDto(
        @JsonProperty
        String name,
        @JsonProperty
        int quantity
) {
}
