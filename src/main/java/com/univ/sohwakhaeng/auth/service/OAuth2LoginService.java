package com.univ.sohwakhaeng.auth.service;

import com.univ.sohwakhaeng.auth.api.dto.KakaoTokenDto;
import com.univ.sohwakhaeng.auth.domain.KakaoUserInfo;
import com.univ.sohwakhaeng.auth.domain.OAuth2UserInfo;
import com.univ.sohwakhaeng.auth.jwt.TokenProvider;
import com.univ.sohwakhaeng.user.Authority;
import com.univ.sohwakhaeng.user.SocialType;
import com.univ.sohwakhaeng.user.User;
import com.univ.sohwakhaeng.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.univ.sohwakhaeng.auth.api.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Value("${KAKAO_CLIENT_ID}")
    private String kakaoClientId;

    @Value("${KAKAO_CLIENT_SECRET}")
    private String kakaoClientSecret;

    @Value("${REDIRECT_URL_KAKAO}")
    private String redirectUrlKakao;

    private static final String AUTHORIZATION_CODE = "authorization_code";

    @Transactional
    public TokenDto proccessOAuth2Login(String provider, String code) {
        if (provider.equals(SocialType.KAKAO.toString())) {
            KakaoTokenDto kakaoTokenDto = getToken(code, kakaoClientId, kakaoClientSecret, redirectUrlKakao,
                    "https://kauth.kakao.com/oauth/token", KakaoTokenDto.class);
            Map<String, Object> attributes = getUserInfo(kakaoTokenDto.accessToken(), "https://kapi.kakao.com/v2/user/me");
            return authenticateUser(new KakaoUserInfo(attributes));
        }
        throw new IllegalArgumentException("지원하지 않는 소셜 로그인입니다.");
    }

    private <T> T getToken(String code, String clientId, String clientSecret, String redirectUri, String tokenUri,
                           Class<T> responseType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", AUTHORIZATION_CODE);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);
        ResponseEntity<T> response = RestClient.create().post()
                .uri(tokenUri)
                .accept(MediaType.APPLICATION_JSON)
                .body(params)
                .retrieve()
                .toEntity(responseType);
        return response.getBody();
    }

    private Map<String, Object> getUserInfo(String accessToken, String userInfoUri) {
        ResponseEntity<Map> response = RestClient.create().get()
                .uri(userInfoUri)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(Map.class);
        return response.getBody();
    }

    private TokenDto authenticateUser(OAuth2UserInfo oAuth2userInfo) {
        User user = userRepository.findByUsername(oAuth2userInfo.getProviderId())
                .orElseGet(() -> userRepository.save(User.builder() // <- DB에 사용자의 정보가 없다면 자동 회원가입
                        .username(oAuth2userInfo.getProviderId())
                        .name(oAuth2userInfo.getName())
                        .nickname(oAuth2userInfo.getName())
                        .password(passwordEncoder.encode(oAuth2userInfo.getProviderId() + oAuth2userInfo.getName()))
                        .socialType(SocialType.of(oAuth2userInfo.getProvider()))
                        .authority(Authority.ROLE_USER)
                        .profileImgUrl(oAuth2userInfo.getProfileImage())
                        .build()));

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),
                        oAuth2userInfo.getProviderId() + oAuth2userInfo.getName())
        );

        return tokenProvider.generateTokenDto(authentication);
    }
}