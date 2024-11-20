package com.univ.sohwakhaeng.auth.api;


import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.univ.sohwakhaeng.auth.api.dto.TokenDto;
import com.univ.sohwakhaeng.auth.service.OAuth2LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2LoginService oAuth2LoginService;

    @PostMapping("/public/{provider}/token")
    public BaseResponse<TokenDto> auth(@PathVariable(value = "provider") String provider, @RequestParam(value = "code") String code) {
        return BaseResponse.success(
                SuccessCode.USER_LOGIN_SUCCESS, oAuth2LoginService.proccessOAuth2Login(provider, code));
    }
}
