package com.univ.sohwakhaeng.enterprise.api;

import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDetailDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseRequestDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.service.EnterpriseService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseService enterPriseService;

    @PostMapping("/public/enterprises")
    public BaseResponse<Void> postEnterprises(@RequestBody List<EnterpriseRequestDto> dtos) throws EnterpriseNotFoundException {
        return BaseResponse.success(
                SuccessCode.GET_ENTERPRISE_DETAILS, enterPriseService.postEnterprises(dtos));
    }

    @GetMapping("/api/enterprises/{enterPriseId}")
    public BaseResponse<EnterpriseDetailDto> getEnterpriseDetails(@PathVariable Long enterPriseId) throws EnterpriseNotFoundException {
        return BaseResponse.success(
                SuccessCode.GET_ENTERPRISE_DETAILS, enterPriseService.getEnterpriseDetails(enterPriseId));
    }

    @GetMapping("/api/enterprises/category/{category}")
    public BaseResponse<PagedResponseDto<EnterpriseOverviewDto>> getEnterprisesByCategory(
            @PathVariable Category category,
            @RequestParam int size,
            @RequestParam int limit
    ){
        return BaseResponse.success(
                SuccessCode.GET_ENTERPRISE_OVERVIEW, enterPriseService.getEnterprisesByCategory(category, PageRequest.of(limit, size)));
    }
}