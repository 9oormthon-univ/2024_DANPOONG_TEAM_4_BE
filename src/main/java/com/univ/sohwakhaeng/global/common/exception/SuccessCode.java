package com.univ.sohwakhaeng.global.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /*201 CREATED*/
    USER_JOIN_SUCCESS(HttpStatus.CREATED, "유저 회원가입 성공"),


    /*200 OK*/
    USER_LOGIN_SUCCESS(HttpStatus.OK, "유저 로그인 성공"),
    USER_PROFILE_SUCCESS(HttpStatus.OK, "유저 프로필 조회 성공"),
    USER_DELETE_SUCCESS(HttpStatus.OK, "회원 탈퇴 완료"),

    // Contract 관련
    POST_CONTRACT(HttpStatus.OK, "계약 등록 성공"),


    // Enterprise 관련
    GET_ENTERPRISE_DETAILS(HttpStatus.OK, "상점 상세 정보 조회 성공"),
    GET_ENTERPRISE_OVERVIEW(HttpStatus.OK, "상점 기본 정보 조회 성공"),

    // Cart 관련
    POST_CART(HttpStatus.OK, "장바구니 등록 성공"),
    GET_CART(HttpStatus.OK, "장바구니 전체 조회 성공")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}