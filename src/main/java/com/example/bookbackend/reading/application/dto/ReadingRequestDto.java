package com.example.bookbackend.reading.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingRequestDto {
    //이건 유저정보에서 조회하는거라 솔직히 필요없음.
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
}
