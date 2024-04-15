package com.example.bookbackend.reading.application.dto;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.reading.domain.Reading;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadingResponseDto {
    private String bookTitle;
    private String nickName;
    private int pageNo;
    private List<Book> books;
    //상단
    /*
        총 읽은책 개수, 읽고 있는 책 개수, 읽을 책 개수
     */
    //하단 내가 읽은 책 (list)
    /*
        목표, 책 제목, 책 내용?, 읽은 페이지(%), 이미지, 완독여부
     */

    //Entity -> dto

    public static ReadingResponseDto toDto(Reading reading) {
        return ReadingResponseDto.builder()
                .bookTitle(reading.getBookTitle())
                .nickName(reading.getNickName())
                .pageNo(reading.getPageNo())
                .build();
    }
}
