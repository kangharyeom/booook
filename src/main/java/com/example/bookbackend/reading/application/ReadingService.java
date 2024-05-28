package com.example.bookbackend.reading.application;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookRepository;
import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.response.ApiCode;
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
    public List<Reading> getReadingHistory(String name) {
        return readingRepository.findByName(name);
    }

    // 내가 읽은 책 정보 반환
    public List<Book> getBookInfo(String name) {
        return bookRepository.findByBookInfo(name);
    }

    //총 읽은 책 개수 계산(진행률이 100%인 책)
    public int getAllBookCounting(String name) {
        //책 계산
        int i = 0;
        List<Book> getData = getBookInfo(name);

        if(getData.isEmpty()) return i;

        for(Book r: getData) {
            if(r.isCompletedReading()) i++;
        }
        return i;
    }

    //읽고 있는 책 개수 계산(진행률이 1% ~ 99%인 책)
    public int getReadingBookCounting(String name) {
        //1. 내가 읽은 책 정보 조회
        int i = 0;
        List<Book> getBookData = getBookInfo(name);

        //2. 내가 읽은 책 기록 조회
        List<Reading> getReadingData = getReadingHistory(name);
        log.info("getBookData {} getReadingData {}", getBookData, getReadingData);

        if(getReadingData.isEmpty() || getBookData.isEmpty()) return i; // 아직 읽고 있는 책 없음

        //3. 읽고 있는 책일 경우 i 값 증가
        //순서 : 완독 여부 판단 -> 읽은 총 페이지가 0이 아니어야 함 -> 책 제목이랑 읽은 히스토리의 책 제목이 일치하면 카운팅
        for(Book b: getBookData) {
            if(!b.isCompletedReading()) {
                if(b.getTotalPageCount() != 0) {
                    for(Reading r : getReadingData) {
                        if(b.getTitle().equals(r.getBookTitle())) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    //읽을 책 계산 (진행률이 0%인 책)
    public int getReadBookCounting(String name) {
        //1. 책 페이지 조회
        int i = 0;
        List<Book> getBookData = getBookInfo(name);
        if(getBookData.isEmpty()) return i;

        for(Book b : getBookData) {
            if(!b.isCompletedReading() && b.getTotalPageCount() == 0) {
                i++;
            }
        }

        return i;
    }

    //메인(종합 데이터)
    public ReadingResponseDto returnMainData(ReadingRequestDto readingRequestDto) {
        //1. 책에 대한 정보 가져오기
        List<Book> bookInfo = getBookInfo(readingRequestDto.getName());

        //2. 총 읽은 책 계산
        int allBook = getAllBookCounting(readingRequestDto.getName());
        //3. 읽고 있는 책 갯수 계산
        int readingBook = getReadingBookCounting(readingRequestDto.getName());
        //4. 읽을 책 계산
        int readBook = getReadBookCounting(readingRequestDto.getName());

        //5. 책에 대한 정보에서 진행률 % 계산
        //TODO (이건 book브랜치와 병합전이라 지금 상태에서 백엔드에서 처리하기 복잡함.)

        return ReadingResponseDto.returnData(allBook, readingBook, readBook, bookInfo);
    }
}
