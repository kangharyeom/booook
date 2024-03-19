package com.example.bookbackend.common.util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ApiCode {
    //정상
    API_0000("0000", "정상 처리되었습니다.", HttpStatus.OK),
    API_9999("9999", "기타 시스템 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    private final String code;
    private final String msg;
    private final HttpStatus status;
    /** **/
}
