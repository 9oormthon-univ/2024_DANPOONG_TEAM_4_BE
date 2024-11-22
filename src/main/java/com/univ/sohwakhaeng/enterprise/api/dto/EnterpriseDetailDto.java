package com.univ.sohwakhaeng.enterprise.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.api.dto.ProductResponseDto;
import java.util.List;
import java.util.function.Function;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기업 상세 정보 DTO")
public record EnterpriseDetailDto(

        @Schema(description = "기업의 ID", example = "1")
        @JsonProperty("enterprise_id")
        Long id,

        @Schema(description = "기업 이미지 URL", example = "https://example.com/enterprise-image.jpg")
        @JsonProperty("enterprise_image_url")
        String imageUrl,

        @Schema(description = "기업 이름", example = "초록믿음강진")
        @JsonProperty("enterprise_name")
        String name,

        @Schema(description = "기업 카테고리", example = "AGRICULTURAL_PRODUCTS")
        @JsonProperty("category")
        Category category,

        @Schema(description = "기업 설명", example = "강진군에서 운영하는 직거래 지원센터로, 지역 농어업인이 직접 생산한 신선한 농수특산물을 판매합니다.")
        @JsonProperty("description")
        String description,

        @Schema(description = "기업 평점", example = "4.5")
        @JsonProperty("star")
        Double star,

        @Schema(description = "기업 위치의 위도", example = "34.6400")
        @JsonProperty("latitude")
        Double latitude,

        @Schema(description = "기업 위치의 경도", example = "126.7700")
        @JsonProperty("longitude")
        Double longitude,

        @Schema(description = "기업 주소", example = "전라남도 강진군 강진읍 오감길 2")
        @JsonProperty("address")
        String address,

        @Schema(description = "기업이 판매하는 제품 목록", example = """
                [
                    {
                        "product_id": 1,
                        "product_name": "유기농 쌀",
                        "unit": "1kg",
                        "image_url": "https://example.com/product-image1.jpg",
                        "price": 5000
                    },
                    {
                        "product_id": 2,
                        "product_name": "감자",
                        "unit": "1kg",
                        "image_url": "https://example.com/product-image2.jpg",
                        "price": 3000
                    }
                ]
                """)
        @JsonProperty("products")
        List<ProductResponseDto> products
) {

    public static EnterpriseDetailDto fromEntity(Enterprise enterprise, String imageUrl, Function<Product, String> getProductImageUrl) {
        return new EnterpriseDetailDto(
                enterprise.getId(),
                imageUrl,
                enterprise.getName(),
                enterprise.getCategory(),
                enterprise.getDescription(),
                enterprise.getStar(),
                enterprise.getLatitude(),
                enterprise.getLongitude(),
                enterprise.getAddress(),
                enterprise.getProducts().stream()
                        .map(product -> {
                            String productImageUrl = getProductImageUrl.apply(product);
                            return ProductResponseDto.fromEntity(product, productImageUrl);
                        })
                        .toList()
        );
    }

}
