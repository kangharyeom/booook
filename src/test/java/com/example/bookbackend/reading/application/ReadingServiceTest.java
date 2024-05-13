package com.example.bookbackend.reading.application;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import com.example.bookbackend.reading.repository.ReadingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ReadingServiceTest {

    @Autowired
    ReadingService readingService;

    @Autowired
    ReadingRepository readingRepository;

    @DisplayName("해당 유저가 읽은 책 기록을 가져온다.")
    @Test
    void getReadingHistory() {
        readingService.getReadingHistory("test");
    }

    @DisplayName("해당 유저가 총 읽은 책이 몇권인지 가져온다.")
    @Test
    void getAllBookCounting() {
        readingService.getAllBookCounting("test");
    }

    @DisplayName("해당 유저가 읽고 있는 책이 몇권인지 가져온다.")
    @Test
    void getReadingBookCounting() {
        readingService.getReadingBookCounting("test");
    }

    @DisplayName("해당 유저가 다 읽은 책이 몇권인지 가져온다.")
    @Test
    void getReadBookCounting() {
        readingService.getReadingBookCounting("test");
    }

    @DisplayName("모든 데이터 반환")
    @Test
    void returnMainData() {
        ReadingRequestDto readingRequestDto = new ReadingRequestDto();
        readingRequestDto.setName("test");
        readingService.returnMainData(readingRequestDto);
    }

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
}