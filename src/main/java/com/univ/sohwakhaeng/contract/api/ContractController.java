package com.univ.sohwakhaeng.contract.api;

import com.univ.sohwakhaeng.auth.domain.PrincipalDetails;
import com.univ.sohwakhaeng.contract.api.dto.ContractDetailDto;
import com.univ.sohwakhaeng.contract.api.dto.ContractReqDto;
import com.univ.sohwakhaeng.contract.api.dto.ContractsInfoDto;
import com.univ.sohwakhaeng.contract.service.ContractService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/contracts")
    public BaseResponse<PagedResponseDto<ContractsInfoDto>> getContracts(@AuthenticationPrincipal PrincipalDetails principal,
                                                                         @RequestParam int page,
                                                                         @RequestParam int limit
    ) {
        return BaseResponse.success(SuccessCode.GET_CONTRACTS, contractService.getMyContracts(principal, PageRequest.of(page, limit)));
    }

    @GetMapping("/contracts/{contractId}")
    public BaseResponse<ContractDetailDto> getContract(@PathVariable Long contractId) {
        return BaseResponse.success(SuccessCode.GET_CONTRACTS, contractService.getContractDetail(contractId));
    }
}