package com.example.bookbackend.reading.web;

import com.example.bookbackend.common.exception.CommonResponse;
import com.example.bookbackend.common.util.ApiCode;
import com.example.bookbackend.reading.application.ReadingService;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import com.example.bookbackend.reading.application.dto.ReadingResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/reading")
public class ReadingController {
    private final ReadingService readingService;

    //ping
    @GetMapping("/ping")
    public CommonResponse ping() {
        return new CommonResponse(ApiCode.API_0000.getCode(), ApiCode.API_0000.getMsg());
    }

    //메인페이지 읽은 책 데이터 조회
    @GetMapping("/getReadingData")
    public CommonResponse getReadingData(@Valid @RequestBody ReadingRequestDto readingRequestDto, BindingResult result) {
        ReadingResponseDto readingResponseDto = readingService.returnMainData(readingRequestDto);
        return new CommonResponse(ApiCode.API_0000.getCode(), ApiCode.API_0000.getMsg());
    }
}
