package com.example.bookbackend.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkerPostDto {
    private long memberId;
    private String date;
    private String text;
}
