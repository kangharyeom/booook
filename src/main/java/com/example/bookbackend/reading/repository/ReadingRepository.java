package com.example.bookbackend.reading.repository;

import com.example.bookbackend.reading.domain.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    List<Reading> findByName(String name);
    List<Reading> findAllByName(String name);
}
