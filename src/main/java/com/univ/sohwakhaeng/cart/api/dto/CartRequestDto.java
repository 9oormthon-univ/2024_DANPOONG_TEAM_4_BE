package com.univ.sohwakhaeng.cart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import java.util.List;

public record CartRequestDto(
        @JsonProperty("enterpriseId")
        Long enterpriseId,

        @JsonProperty("items")
        List<ItemRequestDto> products
) {

}

