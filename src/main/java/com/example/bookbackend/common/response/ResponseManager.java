package com.example.bookbackend.common.response;

import com.example.bookbackend.common.exception.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
public class ResponseManager {

    public static void setUpResponse(HttpServletResponse response, CommonResponse commonResponse, HttpStatus httpStatus) {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(commonResponse));
            response.setStatus(httpStatus.value());
        } catch (IOException e) {
            log.error("error :: ", e);
            response.setStatus(INTERNAL_SERVER_ERROR.value());
        }
    }
}

