package com.univ.sohwakhaeng.product.repository;

import com.univ.sohwakhaeng.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
