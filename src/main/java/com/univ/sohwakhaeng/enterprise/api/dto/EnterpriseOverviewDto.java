package com.univ.sohwakhaeng.enterprise.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;

public record EnterpriseOverviewDto(
        @JsonProperty("enterprise_id")
        Long id,

        @JsonProperty("enterprise_image_url")
        String imageUrl,

        @JsonProperty("enterprise_name")
        String name,

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
