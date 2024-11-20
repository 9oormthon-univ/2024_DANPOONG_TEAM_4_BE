package com.univ.sohwakhaeng.contract.domain.repository;

import com.univ.sohwakhaeng.contract.domain.Contract;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByUserAndEnterprise(User user, Enterprise enterprise);
}
