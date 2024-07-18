package com.musinsa.core.common.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.musinsa.core.common.exception.ApiException;
import com.musinsa.core.common.message.ErrorMessage;
import com.musinsa.core.common.message.ResponseCode;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        if (e instanceof HttpMessageNotReadableException) {
            log.warn("{}", e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.warn("{}", e.getMessage());
        } else {
            log.error("{}", e.getMessage(), e);
        }
        return ResponseEntity
                .status(ResponseCode.RUNTIME_EXCEPTION.getStatus())
                .body(ErrorMessage.builder()
                        .resultUri(request.getRequestURI())
                        .resultCode(ResponseCode.RUNTIME_EXCEPTION.getCode())
                        .resultMsg(ResponseCode.BAD_REQUEST_EXCEPTION.getMessage())
                        .resultDt(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request, final Exception e) {
        if (e instanceof ClientAbortException && "java.io.IOException: Broken pipe".equals(e.getMessage())) {
            log.warn("{}", e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.warn("{}", e.getMessage());
        } else {
            log.error("{}", e.getMessage(), e);
        }

        return ResponseEntity
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ErrorMessage.builder()
                        .resultUri(request.getRequestURI())
                        .resultCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                        .resultMsg(ResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                        .resultDt(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request, final MissingServletRequestParameterException e) {
        log.warn("{}", e.getMessage());
        return ResponseEntity
                .status(ResponseCode.BAD_REQUEST_EXCEPTION.getStatus())
                .body(ErrorMessage.builder()
                        .resultUri(request.getRequestURI())
                        .resultCode(ResponseCode.BAD_REQUEST_EXCEPTION.getCode())
                        .resultMsg(ResponseCode.BAD_REQUEST_EXCEPTION.getMessage())
                        .resultDt(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (Object err : Objects.requireNonNull(e.getDetailMessageArguments())) {
            if (ObjectUtils.isNotEmpty(err)) {
                if (err instanceof ArrayList) {
                    for (Object s : (ArrayList) err) {
                        if (StringUtils.isNotBlank(message.toString())) {
                            message.append("\n")
                                    .append((String) s);
                        } else {
                            message.append((String) s);
                        }
                    }
                } else {
                    message.append(err);
                }
            }
        }
        log.warn("{}", e.getMessage());
        return ResponseEntity
                .status(ResponseCode.BAD_REQUEST_EXCEPTION.getStatus())
                .body(ErrorMessage.builder()
                        .resultUri(request.getRequestURI())
                        .resultCode(ResponseCode.BAD_REQUEST_EXCEPTION.getCode())
                        .resultMsg(message.toString())
                        .resultDt(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorMessage> apiExceptionHandler(HttpServletRequest request, final ApiException e) {
        ResponseCode responseCode = e.getErr();
        log.warn("{}", e.getMessage());

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ErrorMessage.builder()
                        .resultUri(request.getRequestURI())
                        .resultCode(responseCode.getCode())
                        .resultMsg(responseCode.getMessage())
                        .resultDt(LocalDateTime.now())
                        .build());
    }
}

