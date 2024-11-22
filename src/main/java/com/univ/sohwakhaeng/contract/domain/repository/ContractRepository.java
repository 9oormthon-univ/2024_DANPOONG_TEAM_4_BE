package com.univ.sohwakhaeng.contract.domain.repository;

import com.univ.sohwakhaeng.contract.domain.Contract;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByUserAndEnterprise(User user, Enterprise enterprise);

    @Query("SELECT c FROM Contract c WHERE c.user = :user")
    Page<Contract> getPagedContractsByUser(User user, Pageable pageable);
}
