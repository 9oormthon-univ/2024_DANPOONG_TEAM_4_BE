package com.univ.sohwakhaeng.global.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 400 BAD REQUEST
     * */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 헤더값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터값이 입력되지 않았습니다."),
    REQUEST_METHOD_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 메소드가 잘못됐습니다."),
    REQUEST_PARAMETER_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못됐습니다."),
    BAD_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 잘못됐습니다"),
    PAYMENT_FAIL(HttpStatus.BAD_REQUEST, "결제 실패"),
    /**
     * 401 UNAUTHORIZED
     */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다"),

    /**
     * 403 FORBIDDEN
     */
    REFRESH_INVALID(HttpStatus.FORBIDDEN, "Refresh Token이 유효하지 않습니다"),

    /**
     * 404 NOT FOUND
     * */
    USER_NOT_FOUND(HttpStatus.NO_CONTENT, "해당 유저가 존재하지 않습니다"),
    ENTERPRISE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 가게가 존재하지 않습니다"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품이 존재하지 않습니다"),
    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
