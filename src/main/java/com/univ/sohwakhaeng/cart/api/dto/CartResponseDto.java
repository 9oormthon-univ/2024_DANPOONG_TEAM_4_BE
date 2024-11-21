package com.univ.sohwakhaeng.cart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import java.util.List;

public record CartResponseDto(
        @JsonProperty("enterprise")
        EnterpriseOverviewDto enterpriseOverviewDto,

        @JsonProperty("items")
        List<ItemRequestDto> itemRequestDtoList
) {
    public static CartResponseDto fromEntity(Cart cart) {
        return new CartResponseDto(
                EnterpriseOverviewDto.fromEntity(cart.getEnterprise()),
                cart.getItems().stream()
                        .map(ItemRequestDto::fromEntity)
                        .toList()
        );
    }
}
