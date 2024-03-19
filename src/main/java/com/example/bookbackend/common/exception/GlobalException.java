package com.example.bookbackend.common.exception;

import com.example.bookbackend.common.utill.ApiCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{
    private final ApiCode apiCode;

    public GlobalException(ApiCode apiCode) {
        super(apiCode.getMsg());
        this.apiCode = apiCode;
    }
}
