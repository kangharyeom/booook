package com.example.bookbackend.book.exception;

import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.response.ApiCode;

public class BookException extends GlobalException {

    public BookException(ApiCode apiCode) {
        super(apiCode);
    }

    public BookException(String addMessage, ApiCode apiCode) {
        super(addMessage, apiCode);
    }
}
