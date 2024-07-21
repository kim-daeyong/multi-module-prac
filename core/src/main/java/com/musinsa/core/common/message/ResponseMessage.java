package com.musinsa.core.common.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMessage<T> {
    private String resultMsg;

    private String resultCode;

    private T data;

    public ResponseMessage(T data) {
        this.resultMsg = ResponseCode.SUCCESS.getMessage();
        this.resultCode = ResponseCode.SUCCESS.getCode();
        this.data = data;
    }

    public ResponseMessage(ResponseCode responseCode, T data) {
        this.resultMsg = responseCode.getMessage();
        this.resultCode = responseCode.getCode();
        this.data = data;
    }
}

