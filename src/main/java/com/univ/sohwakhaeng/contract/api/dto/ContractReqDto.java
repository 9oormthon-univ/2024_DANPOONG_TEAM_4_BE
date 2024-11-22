package com.univ.sohwakhaeng.contract.api.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "계약 요청 DTO")
public record ContractReqDto(

        @Schema(description = "계약할 기업의 ID", example = "1")
        long enterpriseId,

        @Schema(description = "주당 배송 빈도", example = "2")
        int deliveryWeek,

        @Schema(description = "선호하는 배송 요일", example = "Monday")
        String deliveryDay,

        @Schema(description = "배송 수령 방법", example = "Home Delivery")
        String takeMethod,

        @Schema(description = "계약 요청 기간", example = "6개월")
        String requestedTerm,

        @Schema(description = "계약 총 가격", example = "150000")
        long totalPrice,

        @Schema(description = "계약에 포함된 제품 목록", example = """
                [
                    {
                        "productId": 1,
                        "productName": "유기농 쌀",
                        "unit": "1kg",
                        "price": 5000,
                        "quantity": 10
                    },
                    {
                        "productId": 2,
                        "productName": "감자",
                        "unit": "2kg",
                        "price": 4000,
                        "quantity": 5
                    }
                ]
                """)
        List<ProductDto> products
) {
}
