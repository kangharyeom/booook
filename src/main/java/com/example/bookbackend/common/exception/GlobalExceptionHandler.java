package com.example.bookbackend.common.exception;

import com.example.bookbackend.common.utill.ApiCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity handle(GlobalException exception, HttpServletRequest request) {
        String exceptionSource = extractExceptionSource(exception);
        CommonResponse<?> errorCode = new CommonResponse<>(exception.getApiCode().getCode(), exception.getApiCode().getMsg(), exception.getApiCode().getStatus());

        log.warn(
                "source = {} \n {} = {} \n code = {} \n message = {} \n info = {}",
                exceptionSource,
                request.getMethod(), request.getRequestURI(),
                errorCode.getCode(), errorCode.getMessage(),
                errorCode.getInfo().toString()
        );

        return ResponseEntity.ofNullable(new CommonResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    private String extractExceptionSource(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        if (stackTrace.length > 0) {
            return stackTrace[0].toString();
        }
        return "Unknown Source";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleServerException(Exception exception, HttpServletRequest request) {
        String exceptionSource = extractExceptionSource(exception);

        CommonResponse<?> errorCode = new CommonResponse<>(ApiCode.API_9999.getCode(), ApiCode.API_9999.getCode());

        log.error(
                "source = {} \n {} = {}",
                exceptionSource,
                request.getMethod(), request.getRequestURI(),
                exception
        );

        return ResponseEntity.ofNullable(new CommonResponse(errorCode.getCode(), errorCode.getMessage()));
    }

}