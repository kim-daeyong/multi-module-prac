package com.musinsa.core.common.exception.custom;

import com.musinsa.core.common.exception.ApiException;
import com.musinsa.core.common.message.ResponseCode;

import lombok.Getter;

@Getter
public class DuplicateElementException extends ApiException {

    public DuplicateElementException(ResponseCode err) {
        super(err);
    }

    public DuplicateElementException(ResponseCode err, String message) {
        super(err, message);
    }
}
