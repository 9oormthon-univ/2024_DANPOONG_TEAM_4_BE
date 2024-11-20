package com.univ.sohwakhaeng.contract.api;

import com.univ.sohwakhaeng.auth.domain.PrincipalDetails;
import com.univ.sohwakhaeng.contract.dto.ContractReqDto;
import com.univ.sohwakhaeng.contract.service.ContractService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/contracts")
    public BaseResponse<Void> requestContract(@RequestBody ContractReqDto contractReqDto, @AuthenticationPrincipal PrincipalDetails principal) {
        contractService.acceptContract(contractReqDto, principal);
        return BaseResponse.success(SuccessCode.POST_CONTRACT);
    }
}