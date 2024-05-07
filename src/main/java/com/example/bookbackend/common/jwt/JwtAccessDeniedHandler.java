package com.example.bookbackend.common.jwt;

import com.example.bookbackend.common.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import static com.example.bookbackend.common.util.ResponseManager.*;
import static com.example.bookbackend.common.response.ApiCode.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        setUpResponse(response, CommonResponse.of(API_1001), FORBIDDEN);
    }
}
