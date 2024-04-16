package com.example.bookbackend.member.exception;

import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.response.ApiCode;

public class MemberException extends GlobalException {

    public MemberException(ApiCode apiCode) {
        super(apiCode);
    }

    public MemberException(String message, ApiCode apiCode) {
        super(message, apiCode);
    }
}
