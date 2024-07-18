package com.musinsa.core.common.exception.custom;

import org.apache.commons.lang3.StringUtils;

import com.musinsa.core.common.message.ResponseCode;

import lombok.Getter;

@Getter
public class DuplicateElementException extends RuntimeException {
    private ResponseCode err;

    public DuplicateElementException(ResponseCode err) {
        super(err.getMessage());
        this.err = err;
    }

    public DuplicateElementException(ResponseCode err, String message) {
        super(StringUtils.isNoneBlank(message) ? message : err.getMessage());
        this.err = err;
    }
}
