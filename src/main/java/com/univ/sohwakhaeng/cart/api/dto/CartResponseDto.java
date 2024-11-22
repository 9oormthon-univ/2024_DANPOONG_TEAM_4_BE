package com.univ.sohwakhaeng.cart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import com.univ.sohwakhaeng.item.api.dto.ItemResponseDto;
import com.univ.sohwakhaeng.product.Product;
import java.util.List;
import java.util.function.Function;

public record CartResponseDto(
        @JsonProperty("enterprise")
        EnterpriseOverviewDto enterpriseOverviewDto,

        @JsonProperty("items")
        List<ItemResponseDto> itemRequestDtoList
) {
    public static CartResponseDto fromEntity(Cart cart, String enterpriseImageUrl, Function<Product, String> getProductImageUrlFunction) {
        return new CartResponseDto(
                EnterpriseOverviewDto.fromEntity(cart.getEnterprise(), enterpriseImageUrl),
                cart.getItems().stream()
                        .map(item -> {
                            String productImageUrl = getProductImageUrlFunction.apply(item.getProduct());
                            return ItemResponseDto.fromEntity(item, productImageUrl);
                        })
                        .toList()
        );
    }
}
