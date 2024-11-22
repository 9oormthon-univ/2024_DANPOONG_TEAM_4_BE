package com.univ.sohwakhaeng.cart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import com.univ.sohwakhaeng.item.api.dto.ItemResponseDto;
import com.univ.sohwakhaeng.product.Product;
import java.util.List;
import java.util.function.Function;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장바구니 응답 DTO")
public record CartResponseDto(

        @Schema(description = "기업 개요 정보", example = """
                {
                    "enterprise_id": 1,
                    "enterprise_image_url": "https://example.com/enterprise-image.jpg",
                    "enterprise_name": "초록믿음강진",
                    "category": "AGRICULTURAL_PRODUCTS"
                }
                """)
        @JsonProperty("enterprise")
        EnterpriseOverviewDto enterpriseOverviewDto,

        @Schema(description = "장바구니에 담긴 상품 목록", example = """
                [
                    {
                        "product_id": 1,
                        "product_name": "유기농 쌀",
                        "unit": "1kg",
                        "imageUrl": "https://example.com/product-image.jpg",
                        "quantity": 3
                    },
                    {
                        "product_id": 2,
                        "product_name": "감자",
                        "unit": "1kg",
                        "imageUrl": "https://example.com/product-image2.jpg",
                        "quantity": 2
                    }
                ]
                """)
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
