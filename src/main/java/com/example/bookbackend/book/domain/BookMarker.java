package com.example.bookbackend.book.domain;

import com.example.bookbackend.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BOOK_MARKER")
public class BookMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookMarkerId;
    private String date;
    private String text;

    @Builder
    public BookMarker(long bookMarkerId, String date, String text) {
        this.bookMarkerId = bookMarkerId;
        this.date = date;
        this.text = text;
    }

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
