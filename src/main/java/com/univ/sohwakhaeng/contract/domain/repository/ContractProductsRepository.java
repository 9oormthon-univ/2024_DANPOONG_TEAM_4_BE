package com.univ.sohwakhaeng.contract.domain.repository;

import com.univ.sohwakhaeng.contract.domain.ContractProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractProductsRepository extends JpaRepository<ContractProducts, Long> {
}