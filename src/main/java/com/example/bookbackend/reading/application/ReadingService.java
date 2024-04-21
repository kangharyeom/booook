package com.example.bookbackend.reading.application;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookRepository;
import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.util.ApiCode;
import com.example.bookbackend.member.exception.MemberException;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import com.example.bookbackend.reading.application.dto.ReadingResponseDto;
import com.example.bookbackend.reading.domain.Reading;
import com.example.bookbackend.reading.repository.ReadingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository readingRepository;
    private final BookRepository bookRepository;

    //내가 읽은 책 목록 조회
    public List<ReadingResponseDto> getReadingHistory(String nickName) {
        //내가 읽은 책 조회
        List<Reading> readings = readingRepository.findByNickname(nickName)
                .orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        return readings.stream().map(ReadingResponseDto::toDto).toList();
    }

    //총 읽은 책 개수 계산(진행률이 100%인 책)
    public int getAllBookCounting(String nickName) {
        //책 계산
        List<ReadingResponseDto> getData = getReadingHistory(nickName);
        for(ReadingResponseDto r: getData) {

        }

        return 1;
    }

    //읽고 있는 책 개수 계산(진행률이 1% ~ 99%인 책)
    public int getReadingBookCounting(ReadingRequestDto readingRequestDto) {
        //1. 내가 읽은 책 조회
        List<Reading> readings = readingRepository.findByNickname(readingRequestDto.getNickName())
                .orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        return 1;
    }

    //읽을 책 계산 (진행률이 0%인 책)
    public int getReadBookCounting(ReadingRequestDto readingRequestDto) {
        //1. 내가 읽은 책 조회
        List<Reading> readings = readingRepository.findByNickname(readingRequestDto.getNickName())
                .orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        return 1;
    }

    //메인(종합 데이터)
    public ReadingResponseDto returnMainData(ReadingRequestDto readingRequestDto) {
        //책에 대한 정보 가져오기(파라미터 추가 해야됨.)
        List<Book> books = bookRepository.findByNickname(readingRequestDto.getNickName())
                .orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        Reading reading = Reading.builder().build();

        return ReadingResponseDto.toDto(reading);
    }
}
