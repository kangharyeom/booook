package com.example.bookbackend.auth.exception;

import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.util.ApiCode;

public class AuthException extends GlobalException {

    public AuthException(ApiCode apiCode) {
        super(apiCode);
    }

    public AuthException(String addMessage, ApiCode apiCode) {
        super(addMessage, apiCode);
    }
}
