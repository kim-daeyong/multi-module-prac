package com.musinsa.core.common.exception.custom;

import com.musinsa.core.common.exception.ApiException;
import com.musinsa.core.common.message.ResponseCode;

import lombok.Getter;

@Getter 
public class NotFoundException extends ApiException {

    public NotFoundException(ResponseCode err) {
        super(err);
    }

    public NotFoundException(ResponseCode err, String message) {
        super(err, message);
    }
}
