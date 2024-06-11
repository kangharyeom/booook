package com.example.bookbackend.book.exception;

import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.response.ApiCode;

public class BookMarkerException extends GlobalException {

    public BookMarkerException(ApiCode apiCode) {
        super(apiCode);
    }

    public BookMarkerException(String addMessage, ApiCode apiCode) {
        super(addMessage, apiCode);
    }
}
