package com.univ.sohwakhaeng.cart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "장바구니 요청 DTO")
public record CartRequestDto(

        @Schema(description = "기업의 ID", example = "1")
        @JsonProperty("enterpriseId")
        Long enterpriseId,

        @Schema(description = "장바구니에 담긴 상품 목록", example = """
                [
                    {
                        "productId": 1,
                        "quantity": 3
                    },
                    {
                        "productId": 2,
                        "quantity": 2
                    }
                ]
                """)
        @JsonProperty("items")
        List<ItemRequestDto> products
) {
}
