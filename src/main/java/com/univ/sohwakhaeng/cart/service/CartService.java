package com.univ.sohwakhaeng.cart.service;

import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.cart.api.dto.CartRequestDto;
import com.univ.sohwakhaeng.cart.api.dto.CartResponseDto;
import com.univ.sohwakhaeng.cart.repository.CartRepository;
import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.enterprise.service.EnterpriseService;
import com.univ.sohwakhaeng.item.Item;
import com.univ.sohwakhaeng.item.api.dto.ItemRequestDto;
import com.univ.sohwakhaeng.item.service.ItemService;
import com.univ.sohwakhaeng.user.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final EnterpriseService enterpriseService;
    private final ItemService itemService;

    @Transactional
    public Long saveCart(CartRequestDto requestDto, User user) throws EnterpriseNotFoundException {
        Enterprise enterprise = enterpriseService.getEnterpriseEntityById(requestDto.enterpriseId());
        List<ItemRequestDto> itemRequestDtoList = requestDto.products();
        Cart cart = Cart.createCart(enterprise, user);

        List<Item> itemList = itemService.saveItems(itemRequestDtoList, cart);
        cart.updateItems(itemList);
        return saveCartEntity(cart);
    }

    @Transactional(readOnly = true)
    public List<CartResponseDto> getMyCarts(User user) {
        List<Cart> carts = getAllCartByUserId(user.getId());
        return carts.stream()
                .map(CartResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    private Long saveCartEntity(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    private List<Cart> getAllCartByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
