package com.univ.sohwakhaeng.enterprise.service;

import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDetailDto;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseOverviewDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import com.univ.sohwakhaeng.global.common.dto.PagedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.ENTERPRISE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    @Transactional(readOnly = true)
    public EnterpriseDetailDto getEnterpriseDetails(Long enterpriseId) throws EnterpriseNotFoundException {
        Enterprise enterprise = findEnterpriseEntityById(enterpriseId);
        return EnterpriseDetailDto.fromEntity(enterprise);
    }

    @Transactional(readOnly = true)
    public PagedResponseDto<EnterpriseOverviewDto> getEnterprisesByCategory(Category category, Pageable pageable) {
        Page<Enterprise> enterprises = getPagedEnterprisesByCategory(category, pageable);
        Page<EnterpriseOverviewDto> enterpriseOverviewDtos = enterprises.map(EnterpriseOverviewDto::fromEntity);
        return new PagedResponseDto<>(enterpriseOverviewDtos);
    }

    private Enterprise findEnterpriseEntityById(Long id) throws EnterpriseNotFoundException {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException(ENTERPRISE_NOT_FOUND.getMessage()));
    }

    private Page<Enterprise> getPagedEnterprisesByCategory(Category category, Pageable pageable) {
        return enterpriseRepository.getPagedEnterprisesByCategory(category, pageable);
    }

}
