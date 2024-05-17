package com.example.bookbackend.reading.application;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookRepository;
import com.example.bookbackend.member.domain.Member;
import com.example.bookbackend.member.domain.Role;
import com.example.bookbackend.member.repository.MemberRepository;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import com.example.bookbackend.reading.application.dto.ReadingResponseDto;
import com.example.bookbackend.reading.domain.Reading;
import com.example.bookbackend.reading.repository.ReadingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ReadingServiceTest {

    @Autowired
    ReadingService readingService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    ReadingRepository readingRepository;

    @DisplayName("해당 유저가 읽은 책 기록을 가져온다.")
    @Test
    void getReadingHistory() {
        // Given
        String name = "test";
        List<Reading> readings = new ArrayList<>();

        when(readingRepository.findByName(name))
                .thenReturn(Optional.of(readings));

        //when
        List<Reading> result = readingService.getReadingHistory(name);

        //then
        assertEquals(readings, result);
    }

    // TODO 오류 해결 진행중
//    @DisplayName("현재 읽고 있는 책에 대한 모든 데이터 반환")
//    @Test
//    void returnMainData() throws InterruptedException {
//        // Given
//        ReadingRequestDto readingRequestDto = new ReadingRequestDto();
//        readingRequestDto.setName("test");
//
//        bookRepository.save(createTestData());
//        Thread.sleep(3000);
//        readingRepository.save(createReading());
//        Thread.sleep(3000);
//        memberRepository.save(createMember());
//        Thread.sleep(3000);
//
//        List<Book> bookInfo = new ArrayList<>();
//        int allBook = 0; // 총 읽은 책
//        int readingBook = 0; // 읽고 있는 책 갯수
//        int readBook = 0; // 읽을 책 계산
//
////        when(bookRepository.save(createTestData()))
////                .thenReturn(book);
////        when(readingRepository.save(reading))
////                .thenReturn(reading);
//
//        bookRepository.findByMember_Name(readingRequestDto.getName());
//        //when().thenReturn(bookInfo);
//
//        System.out.println(bookRepository.findByMember_Name(readingRequestDto.getName()));
//
//        when(readingService.getAllBookCounting(readingRequestDto.getName()))
//                .thenReturn(allBook);
//
//        when(readingService.getReadingBookCounting(readingRequestDto.getName()))
//                .thenReturn(readingBook);
//
//        when(readingService.getReadBookCounting(readingRequestDto.getName()))
//                .thenReturn(readBook);
//
//        ReadingResponseDto returnData = ReadingResponseDto.returnData(allBook, readingBook, readBook, bookInfo);
//
//        // When
//        ReadingResponseDto responseDto = readingService.returnMainData(readingRequestDto);
//
//
//        System.out.println(returnData);
//        System.out.println(responseDto);
//
//        assertEquals(returnData, responseDto);
//    }

    private Book createTestData() {
        Book book = new Book();
        book.setTitle("편의점 가는 기분");
        book.setCompletedReading(true);
        book.setStartDate("2024-02-01");
        book.setEndDate("2024-05-31");
        book.setImageUrl("www.naver.com");
        book.setSummary("편의점 가는 기분");
        book.setText("편의점 가는 기분");
        book.setTotalPageCount(300);
        book.setGoalPageCount(300);
        return book;
    }

    private Member createMember() {
        return Member.builder()
                .email("test@naver.com")
                .name("test")
                .tel("010-0000-0000")
                .password("1234")
                .role(Role.ROLE_USER)
                .build();
    }

    private Reading createReading() {
        return Reading.builder()
                .bookTitle("편의점 가는 기분")
                .name("test")
                .pageNo(20)
                .build();
    }
}