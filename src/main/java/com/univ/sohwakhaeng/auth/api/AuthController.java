package com.univ.sohwakhaeng.auth.api;


import com.univ.sohwakhaeng.global.common.dto.BaseResponse;
import com.univ.sohwakhaeng.global.common.exception.SuccessCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.univ.sohwakhaeng.auth.api.dto.TokenDto;
import com.univ.sohwakhaeng.auth.service.OAuth2LoginService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2LoginService oAuth2LoginService;

    @GetMapping("/oauth/callback/kakao")
    public BaseResponse<Void> auth(@RequestParam(value = "code") String code, HttpServletResponse response) throws IOException {
        String token = oAuth2LoginService.proccessOAuth2Login(code).accessToken();
        String redirectUrl = "http://localhost:5137/main?token=" + token;
        response.sendRedirect(redirectUrl);
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS);
    }
}
