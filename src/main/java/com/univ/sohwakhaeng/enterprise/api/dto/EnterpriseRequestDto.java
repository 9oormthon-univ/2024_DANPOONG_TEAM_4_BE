package com.univ.sohwakhaeng.enterprise.api.dto;

import com.univ.sohwakhaeng.enterprise.Enterprise;
import lombok.Builder;

@Builder
public record EnterpriseRequestDto(
        Long enterpriseId,
        String enterpriseName,
        String category,
        String description,
        Double star,
        Double latitude,
        Double longitude,
        String address
) {

}
