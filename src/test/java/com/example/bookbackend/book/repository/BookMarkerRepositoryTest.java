package com.example.bookbackend.book.repository;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookMarker;
import com.example.bookbackend.book.domain.BookMarkerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class BookMarkerRepositoryTest {
    @Autowired
    private BookMarkerRepository bookMarkerRepository;

    @DisplayName("책 정보를 가져온다.")
    @Test
    void findBookById() {
        //given
        BookMarker bookMarker = createBook();
        BookMarker saveBook = bookMarkerRepository.save(bookMarker);

        //when
        BookMarker findBookMarker = bookMarkerRepository.findById(saveBook.getBookMarkerId())
                .orElseThrow(()-> new IllegalArgumentException("잘못된 아이디 입니다."));

        //then
        assertThat(findBookMarker.getDate()).isEqualTo("2024-05-21");
    }

    private BookMarker createBook() {
        return BookMarker.builder()
                .bookMarkerId(1L)
                .date("2024-05-21")
                .text("오늘의 글귀는 너무 감명깊었다.")
                .build();
    }
}
