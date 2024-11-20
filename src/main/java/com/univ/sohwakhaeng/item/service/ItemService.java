package com.univ.sohwakhaeng.item.service;

import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.item.Item;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import com.univ.sohwakhaeng.item.repository.ItemRepository;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.product.exception.ProductNotFoundException;
import com.univ.sohwakhaeng.product.service.ProductService;
import com.univ.sohwakhaeng.user.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProductService productService;

    @Transactional
    public List<Item> saveItems(List<ItemRequestDto> itemRequestDtoList, Cart cart) {
        List<Item> items = itemRequestDtoList.stream()
                .map(dto -> {
                    Product product = null;
                    try {
                        product = productService.getProductById(dto.productId());
                    } catch (ProductNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return Item.fromDto(product, dto.quantity(), cart);
                })
                .collect(Collectors.toList());
        items.forEach(this::saveItem);

        return items;
    }

    private void saveItem(Item item) {
        itemRepository.save(item);
    }

}
