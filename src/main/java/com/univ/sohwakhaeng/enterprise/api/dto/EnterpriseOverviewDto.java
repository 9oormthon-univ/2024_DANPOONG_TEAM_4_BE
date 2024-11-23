package com.univ.sohwakhaeng.enterprise.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;

@Schema(description = "기업 요약 정보 DTO")
public record EnterpriseOverviewDto(

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
        Category category
) {

    public static EnterpriseOverviewDto fromEntity(Enterprise enterprise, String imageUrl) {
        return new EnterpriseOverviewDto(
                enterprise.getId(),
                imageUrl,
                enterprise.getName(),
                enterprise.getCategory()
        );
    }

}
