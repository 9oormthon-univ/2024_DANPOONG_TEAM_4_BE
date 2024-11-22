package com.univ.sohwakhaeng.enterprise.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDetailDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.service.ProductService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.ENTERPRISE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final ProductService productService;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String awsBucket;

    @Transactional(readOnly = true)
    public EnterpriseDetailDto getEnterpriseDetails(Long enterpriseId) throws EnterpriseNotFoundException {
        Enterprise enterprise = findEnterpriseEntityById(enterpriseId);
        Function<Product, String> getProductImageUrl = product -> productService.getProductImageUrl(product.getName());
        return EnterpriseDetailDto.fromEntity(enterprise, getEnterpriseImageUrl(enterprise.getName()), getProductImageUrl);
    }

    @Transactional(readOnly = true)
    public PagedResponseDto<EnterpriseOverviewDto> getEnterprisesByCategory(Category category, Pageable pageable) {
        Page<Enterprise> enterprises = getPagedEnterprisesByCategory(category, pageable);
        Page<EnterpriseOverviewDto> enterpriseOverviewDtos = enterprises.map(enterprise -> {
            String imageUrl = getEnterpriseImageUrl(enterprise.getName());
            return EnterpriseOverviewDto.fromEntity(enterprise, imageUrl);
        });        return new PagedResponseDto<>(enterpriseOverviewDtos);
    }

    @Transactional(readOnly = true)
    public Enterprise getEnterpriseEntityById(Long id) throws EnterpriseNotFoundException {
        return findEnterpriseEntityById(id);
    }

    private Enterprise findEnterpriseEntityById(Long id) throws EnterpriseNotFoundException {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException(ENTERPRISE_NOT_FOUND.getMessage()));
    }

    private Page<Enterprise> getPagedEnterprisesByCategory(Category category, Pageable pageable) {
        return enterpriseRepository.getPagedEnterprisesByCategory(category, pageable);
    }

    public String getEnterpriseImageUrl(String enterpriseName) {
        return amazonS3Client.getUrl(awsBucket, enterpriseName).toString();
    }

}
