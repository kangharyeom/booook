package com.example.bookbackend.reading.web;

import com.example.bookbackend.common.response.ApiCode;
import com.example.bookbackend.common.response.CommonResponse;
import com.example.bookbackend.reading.application.ReadingService;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "메인 api Response", description = "book API")
@RequestMapping("/v1/api/reading")
public class ReadingController {
    private final ReadingService readingService;

    //ping
    @GetMapping("/ping")
    @Operation(summary = "ping", description = "ping API")
    public CommonResponse ping() {
        return new CommonResponse(ApiCode.API_0000.getCode(), ApiCode.API_0000.getMsg());
    }

    //메인페이지 읽은 책 데이터 조회
    @PostMapping("/getReadingData")
    @Operation(summary = "main api", description = "메인 페이지 유저 정보 API")
    public CommonResponse getReadingData(@Valid @RequestBody ReadingRequestDto readingRequestDto, BindingResult result) {
        // 검증오류 체크
        if(result.hasErrors()) {
            log.error("result 검증 오류 : {} ", result.getFieldError().getDefaultMessage());
            return new CommonResponse(ApiCode.API_9999.getCode(), result.getFieldError().getDefaultMessage());
        }

        //return
        return new CommonResponse(ApiCode.API_0000.getCode(), ApiCode.API_0000.getMsg(), readingService.returnMainData(readingRequestDto));
    }
}
