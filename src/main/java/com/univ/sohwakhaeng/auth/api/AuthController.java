package com.univ.sohwakhaeng.auth.api;


import com.univ.sohwakhaeng.auth.api.dto.KakaoLoginDto;
import com.univ.sohwakhaeng.auth.api.dto.TokenDto;
import com.univ.sohwakhaeng.auth.service.OAuth2LoginService;
import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2LoginService oAuth2LoginService;

    @PostMapping("/public/login")
    public BaseResponse<TokenDto> auth(@RequestBody KakaoLoginDto kakaoLoginDto) throws IOException {;
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS, oAuth2LoginService.proccessOAuth2Login(kakaoLoginDto));
    }
}
