package com.musinsa.core.common.exception;

import org.apache.commons.lang3.StringUtils;

import com.musinsa.core.common.message.ResponseCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ResponseCode err;

    public ApiException(ResponseCode err) {
        super(err.getMessage());
        this.err = err;
    }

    public ApiException(ResponseCode err, String message) {
        super(StringUtils.isNoneBlank(message) ? message : err.getMessage());
        this.err = err;
    }
}
