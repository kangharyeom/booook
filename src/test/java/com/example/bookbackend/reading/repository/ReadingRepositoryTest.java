package com.example.bookbackend.reading.repository;

import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.util.ApiCode;
import com.example.bookbackend.reading.domain.Reading;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class ReadingRepositoryTest {

    @Autowired
    ReadingRepository readingRepository;

    @DisplayName("특정 유저가 얼만큼 읽었는지 가져온다.")
    @Test
    void findByName() {
        //given
        Reading reading = Reading.builder()
                .bookTitle("편의점 가는 기분")
                .name("test")
                .pageNo(20)
                .build();

        //when
        try {
            readingRepository.save(reading);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB error : " + e.getMessage());
        }

        List<Reading> readings = readingRepository.findByName("test").
                orElseThrow(() -> new GlobalException(ApiCode.API_9999));

        //then
        assertNotNull(readings);

        for(Reading r : readings) {
            assertThat(r.getName()).isEqualTo("test");
        }
    }

    @Test
    void findByBookTitle() {
    }
}