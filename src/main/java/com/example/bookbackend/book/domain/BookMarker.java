package com.example.bookbackend.book.domain;

import com.example.bookbackend.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BOOK_MARKER")
public class BookMarker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookMarkerId;
    private String date;
    private String text;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
