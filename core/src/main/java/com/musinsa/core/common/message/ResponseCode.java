package com.musinsa.core.common.message;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResponseCode {
    SUCCESS(HttpStatus.OK,"0000","성공")

    // 공통
    , RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "000001","Bad Request")
    , NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "000002","Bad Request")
    , AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED, "000003","UNAUTHORIZED")
    , RUNTIME_FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN, "000004","Forbidden")
    , RUNTIME_REQUEST_URI_EXCEPTION(HttpStatus.URI_TOO_LONG, "000005","Request-URI too long")
    , RUNTIME_PARAM_EXCEPTION(HttpStatus.BAD_REQUEST, "000006","필수항목 오류")
    , RUNTIME_REQUEST_MESSAGE_EXCEPTION(HttpStatus.BAD_REQUEST, "000007","메시지 Format 오류")
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "000008","시스템 오류")
    , INTERNAL_SERVICE_UNAVAILABLE_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "000009","Service Unavailable")
    , JSON_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "0000010","Json 파싱 에러")

    // 브랜드
    , EXIST_BRAND_NAME_EXCEPTION(HttpStatus.CONFLICT, "011001","이미 존재하는 브랜드 이름입니다.")

    ;
    private final HttpStatus status;
    private final String code;
    private String message;

    ResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

