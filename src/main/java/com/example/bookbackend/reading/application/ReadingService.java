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
import java.util.Optional;

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
        int i = 0;
        Optional<List<Book>> getData = bookRepository.findByMember_Name(nickName);

        if(getData.isEmpty()) return i;

        for(Book r: getData.get()) {
            if(r.isCompletedReading()) i++;
        }
        return i;
    }

    //읽고 있는 책 개수 계산(진행률이 1% ~ 99%인 책)
    public int getReadingBookCounting(String nickName) {
        //1. 내가 읽은 책 조회
        int i = 0;
        Optional<List<Book>> getBookData = bookRepository.findByMember_Name(nickName);

        //2. 책 페이지 조회
        List<ReadingResponseDto> getReadingData = getReadingHistory(nickName);
        if(getReadingData.isEmpty() || getBookData.isEmpty()) return i; // 아직 읽고 있는 책 없음

        for(Book b: getBookData.get()) {
            for(ReadingResponseDto r : getReadingData) {
                if(b.getTitle().equals(r.getBookTitle())) {
                    if(!b.isCompletedReading())
                        if(r.getPageNo() != 0) i++;
                }
            }
        }
        return i;
    }

    //읽을 책 계산 (진행률이 0%인 책)
    public int getReadBookCounting(String nickName) {
        //1. 책 페이지 조회
        int i = 0;
        Optional<List<Book>> getBookData = bookRepository.findByMember_Name(nickName);
        if(getBookData.isEmpty()) return i;

        for(Book b : getBookData.get()) {
            if(!b.isCompletedReading() && b.getTotalPageCount() == 0) {
                i++;
            }
        }

        return i;
    }

    //메인(종합 데이터)
    public ReadingResponseDto returnMainData(ReadingRequestDto readingRequestDto) {
        //책에 대한 정보 가져오기(파라미터 추가 해야됨.)
        List<Book> books = bookRepository.findByMember_Name(readingRequestDto.getNickName())
                .orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        Reading reading = Reading.builder().build();

        return ReadingResponseDto.toDto(reading);
    }
}
