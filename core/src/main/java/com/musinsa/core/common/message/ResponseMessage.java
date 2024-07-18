package com.musinsa.core.common.message;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ResponseMessage<T> {
    private T data;

    public ResponseMessage(T data) {
        this.data = data;
    }
}

