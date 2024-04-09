package com.example.bookbackend.common.util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ApiCode {
    //정상
    API_0000("0000", "정상 처리되었습니다.", OK),

    // member
    API_1000("1000", "중복되는 이메일입니다.", BAD_REQUEST),
    API_1001("1001", "일치하는 회원이 없습니다.", BAD_REQUEST),

    // etc
    API_9999("9999", "기타 시스템 오류입니다.", INTERNAL_SERVER_ERROR);

    private String code;
    private String msg;
    private HttpStatus status;
}
