package com.univ.sohwakhaeng.auth.service;

import com.univ.sohwakhaeng.auth.api.dto.KakaoLoginDto;
import com.univ.sohwakhaeng.auth.api.dto.TokenDto;
import com.univ.sohwakhaeng.auth.jwt.TokenProvider;
import com.univ.sohwakhaeng.user.Authority;
import com.univ.sohwakhaeng.user.SocialType;
import com.univ.sohwakhaeng.user.User;
import com.univ.sohwakhaeng.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto proccessOAuth2Login(KakaoLoginDto kakaoLoginDto) {
        return authenticateUser(kakaoLoginDto);
    }

    private TokenDto authenticateUser(KakaoLoginDto kakaoLoginDto) {
        User user = getUserDomain(kakaoLoginDto);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),
                        kakaoLoginDto.providerId() + kakaoLoginDto.nickname())
        );
        return tokenProvider.generateTokenDto(authentication);
    }

    private User getUserDomain(KakaoLoginDto kakaoLoginDto) {
        return userRepository.findByUsername(kakaoLoginDto.providerId())
                .orElseGet(() -> userRepository.save(User.builder() // <- DB에 사용자의 정보가 없다면 자동 회원가입
                        .username(kakaoLoginDto.providerId())
                        .name(kakaoLoginDto.nickname())
                        .nickname(kakaoLoginDto.nickname())
                        .password(passwordEncoder.encode(kakaoLoginDto.providerId() + kakaoLoginDto.nickname()))
                        .socialType(SocialType.of("kakao"))
                        .authority(Authority.ROLE_USER)
                        .profileImgUrl(kakaoLoginDto.profileUrl())
                        .build()));
    }
}