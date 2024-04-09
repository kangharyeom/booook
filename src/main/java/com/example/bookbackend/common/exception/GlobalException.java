package com.example.bookbackend.common.exception;

import com.example.bookbackend.common.util.ApiCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{

    private ApiCode apiCode;
    private String addMessage;

    public GlobalException(ApiCode apiCode) {
        super(apiCode.getMsg());
        this.apiCode = apiCode;
    }

    public GlobalException(String addMessage, ApiCode apiCode) {
        super(apiCode.getMsg() + " " + addMessage);
        this.apiCode = apiCode;
        this.addMessage = addMessage;
    }
}