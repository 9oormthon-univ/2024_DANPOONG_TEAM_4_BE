package com.univ.sohwakhaeng.cart.api;

import com.univ.sohwakhaeng.auth.domain.PrincipalDetails;
import com.univ.sohwakhaeng.cart.api.dto.CartRequestDto;
import com.univ.sohwakhaeng.cart.service.CartService;
import com.univ.sohwakhaeng.enterprise.exception.EnterpriseNotFoundException;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import com.univ.sohwakhaeng.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/api/carts")
    public BaseResponse<Long> saveCart(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CartRequestDto requestDto) throws EnterpriseNotFoundException {
        User user = principalDetails.getUser();
        return BaseResponse.success(SuccessCode.POST_CART, cartService.saveCart(requestDto, user));
    }


}