package com.univ.sohwakhaeng.contract.service;

import com.univ.sohwakhaeng.auth.domain.PrincipalDetails;
import com.univ.sohwakhaeng.contract.api.dto.ContractDetailDto;
import com.univ.sohwakhaeng.contract.api.dto.ContractProducsResDto;
import com.univ.sohwakhaeng.contract.api.dto.ContractReqDto;
import com.univ.sohwakhaeng.contract.api.dto.ContractsInfoDto;
import com.univ.sohwakhaeng.contract.domain.Contract;
import com.univ.sohwakhaeng.contract.domain.ContractProducts;
import com.univ.sohwakhaeng.contract.domain.MethodToTake;
import com.univ.sohwakhaeng.contract.domain.repository.ContractProductsRepository;
import com.univ.sohwakhaeng.contract.domain.repository.ContractRepository;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import com.univ.sohwakhaeng.enterprise.service.EnterpriseService;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.repository.ProductRepository;
import com.univ.sohwakhaeng.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final EnterpriseService enterpriseService;
    private final ContractRepository contractRepository;
    private final ContractProductsRepository contractProductsRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void acceptContract(ContractReqDto contractReqDto, PrincipalDetails principal) {
        Contract contract = extractContractEntity(contractReqDto, principal);
        contractReqDto.products().forEach(product -> {
            Product productEntity = productRepository.findById(product.productId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
            contractProductsRepository.save(ContractProducts.builder()
                    .productName(product.productName())
                    .quantity(product.quantity())
                    .product(productEntity)
                    .contract(contract)
                    .build());
        });

    }

    @Transactional
    protected Contract extractContractEntity(ContractReqDto contractReqDto, PrincipalDetails principal) {
        User user = principal.getUser();
        Enterprise enterprise = enterpriseRepository.findById(contractReqDto.enterpriseId())
                .orElseThrow(() -> new IllegalArgumentException("해당 업체가 존재하지 않습니다."));
        contractRepository.save(Contract.builder()
                .deliveryWeek(contractReqDto.deliveryWeek())
                .user(user)
                .enterprise(enterprise)
                .deliveryDay(contractReqDto.deliveryDay())
                .takeMethod(MethodToTake.of(contractReqDto.takeMethod()))
                .requestedTerm(contractReqDto.requestedTerm())
                .totalPrice(contractReqDto.totalPrice())
                .status(true)
                .build());
        return contractRepository.findByUserAndEnterprise(user, enterprise)
                .orElseThrow(() -> new IllegalArgumentException("해당 계약이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public PagedResponseDto<ContractsInfoDto> getMyContracts(PrincipalDetails principal, Pageable pageable) {
        User user = principal.getUser();
        Page<Contract> contracts = getPagedContractsByUser(pageable, user);
        Page<ContractsInfoDto> contractsInfoDtos = contracts.map(this::convertToDtoFromContract);
        return new PagedResponseDto<>(contractsInfoDtos);
    }

    private Page<Contract> getPagedContractsByUser(Pageable pageable, User user) {
        return contractRepository.getPagedContractsByUser(user, pageable);
    }

    private ContractsInfoDto convertToDtoFromContract(Contract contract) {
        String enterpriseImageUrl = enterpriseService.getEnterpriseImageUrl(contract.getEnterprise().getName());

        return ContractsInfoDto.builder()
                .contractId(contract.getId())
                .enterpriseId(contract.getEnterprise().getId())
                .enterpriseName(contract.getEnterprise().getName())
                .category(contract.getEnterprise().getCategory().toString())
                .profileImage(enterpriseImageUrl)
                .build();
    }

    //계약 상세 조회
    @Transactional(readOnly = true)
    public ContractDetailDto getContractDetail(long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("해당 계약이 존재하지 않습니다."));

        String enterpriseImageUrl = enterpriseService.getEnterpriseImageUrl(contract.getEnterprise().getName());
        return ContractDetailDto.builder()
                .enterpriseId(contract.getEnterprise().getId())
                .requestTerm(contract.getRequestedTerm())
                .regularDelivery(contract.getDeliveryWeek() + " " + contract.getDeliveryDay())
                .profileImgUrl(enterpriseImageUrl)
                .enterpriseName(contract.getEnterprise().getName())
                .products(contract.getContractProducts().stream().map(this::convertToDtoFromContractProducts).toList())
                .category(contract.getEnterprise().getCategory().toString())
                .build();
    }

    private ContractProducsResDto convertToDtoFromContractProducts(ContractProducts contractProduct) {
        return ContractProducsResDto.builder()
                .name(contractProduct.getProductName())
                .quantity(contractProduct.getQuantity())
                .build();
    }
}
