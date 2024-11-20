package com.univ.sohwakhaeng.enterprise.api;

import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.service.EnterpriseService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseService enterPriseService;

    @GetMapping("/api/enterprises/{enterPriseId}")
    public BaseResponse<EnterpriseDto> getEnterpriseDetails(@PathVariable Long enterPriseId) throws EnterpriseNotFoundException {
        return BaseResponse.success(
                SuccessCode.GET_ENTERPRISE_DETAILS, enterPriseService.getEnterpriseDetails(enterPriseId));
    }
}