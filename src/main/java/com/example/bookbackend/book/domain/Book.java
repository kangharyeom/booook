package com.example.bookbackend.book.domain;

import com.example.bookbackend.common.domain.BaseTimeEntity;
import com.example.bookbackend.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BOOK")
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    @NotBlank(message = "제목은 필수 입력 값 입니다.")
    private String title;
    private String startDate;
    private String endDate;
    private String summary;
    private String text;
    private String imageUrl;
    private int totalPageCount;
    private int goalPageCount;
    private boolean completedReading;

    @Builder
    public Book(long bookId, String title, String startDate, String endDate, String summary, String text, String imageUrl, int totalPageCount, int goalPageCount, boolean completedReading) {
        this.bookId = bookId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.summary = summary;
        this.text = text;
        this.imageUrl = imageUrl;
        this.totalPageCount = totalPageCount;
        this.goalPageCount = goalPageCount;
        this.completedReading = completedReading;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<BookMarker> bookMarkerList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
