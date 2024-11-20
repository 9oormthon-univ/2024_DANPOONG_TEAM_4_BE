package com.univ.sohwakhaeng.enterprise.service;

import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseDto;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.univ.sohwakhaeng.global.common.exception.ErrorCode.ENTERPRISE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    @Transactional(readOnly = true)
    public EnterpriseDto getEnterpriseDetails(Long enterpriseId) throws EnterpriseNotFoundException {
        Enterprise enterprise = findEnterpriseEntityById(enterpriseId);
        return EnterpriseDto.fromEntity(enterprise);
    }

    private Enterprise findEnterpriseEntityById(Long id) throws EnterpriseNotFoundException {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException(ENTERPRISE_NOT_FOUND.getMessage()));
    }

}
