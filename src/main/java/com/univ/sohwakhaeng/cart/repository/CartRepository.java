package com.univ.sohwakhaeng.cart.repository;

import com.univ.sohwakhaeng.cart.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserId(Long userId);

}
