package com.musinsa.core.common.message;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String resultUri;
    private String resultCode;
    private String resultMsg;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime resultDt;

    @Builder
    public ErrorMessage(HttpStatus status, String resultUri, String resultCode, String resultMsg, LocalDateTime resultDt){
        this.resultUri = resultUri;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultDt = resultDt;
    }
}

