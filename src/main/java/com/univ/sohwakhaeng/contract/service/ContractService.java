package com.univ.sohwakhaeng.contract.service;

import com.univ.sohwakhaeng.auth.domain.PrincipalDetails;
import com.univ.sohwakhaeng.contract.domain.Contract;
import com.univ.sohwakhaeng.contract.domain.ContractProducts;
import com.univ.sohwakhaeng.contract.domain.MethodToTake;
import com.univ.sohwakhaeng.contract.domain.repository.ContractProductsRepository;
import com.univ.sohwakhaeng.contract.domain.repository.ContractRepository;
import com.univ.sohwakhaeng.contract.dto.ContractReqDto;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.repository.ProductRepository;
import com.univ.sohwakhaeng.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractProductsRepository contractProductsRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final ProductRepository productRepository;

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

    private Contract extractContractEntity(ContractReqDto contractReqDto, PrincipalDetails principal) {
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


}
