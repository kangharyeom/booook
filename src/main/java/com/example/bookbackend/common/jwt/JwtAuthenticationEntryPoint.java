package com.example.bookbackend.common.jwt;

import com.example.bookbackend.common.response.CommonResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.bookbackend.common.jwt.JwtAuthenticationFilter.*;
import static com.example.bookbackend.common.util.ResponseManager.*;
import static com.example.bookbackend.common.response.ApiCode.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        CommonResponse exceptionResponse = createExceptionMessage((Exception) request.getAttribute(EXCEPTION_KEY));
        setUpResponse(response, exceptionResponse, UNAUTHORIZED);
    }

    private CommonResponse createExceptionMessage(Exception e) {
        if (e instanceof SignatureException) {
            return CommonResponse.of(API_1003);
        }

        if (e instanceof MalformedJwtException) {
            return CommonResponse.of(API_1005);
        }

        if (e instanceof ExpiredJwtException) {
            return CommonResponse.of(API_1002);
        }

        if (e instanceof IllegalStateException) {
            return CommonResponse.of(API_1004);
        }

        return CommonResponse.of(API_1000);
    }
}
