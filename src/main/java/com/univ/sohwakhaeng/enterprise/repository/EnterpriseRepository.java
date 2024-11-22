package com.univ.sohwakhaeng.enterprise.repository;

import com.univ.sohwakhaeng.enterprise.Category;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    @Query("SELECT e FROM Enterprise e WHERE e.category = :category")
    Page<Enterprise> getPagedEnterprisesByCategory(Category category, Pageable pageable);

}
