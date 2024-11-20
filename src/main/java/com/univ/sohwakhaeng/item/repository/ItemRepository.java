package com.univ.sohwakhaeng.item.repository;

import com.univ.sohwakhaeng.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
