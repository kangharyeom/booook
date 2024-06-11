package com.example.bookbackend.book.application;

import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookMarker;
import com.example.bookbackend.book.domain.BookMarkerRepository;
import com.example.bookbackend.book.exception.BookMarkerException;
import com.example.bookbackend.common.exception.GlobalException;
import com.example.bookbackend.common.response.ApiCode;
import com.example.bookbackend.member.domain.Member;
import com.example.bookbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookMarkerService {
    private final BookMarkerRepository bookMarkerRepository;
    private final BookService bookService;
    private final MemberRepository memberRepository;

    public BookMarker postBookMarker(BookMarker bookMarker, long bookId, long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new BookMarkerException("Book Marker::" + bookMarker, ApiCode.API_2001));
        Book book = bookService.verifiedBook(bookId);

        bookMarker.setMember(member);
        bookMarker.setBook(book);
        bookMarkerRepository.save(bookMarker);
        return bookMarker;
    }

    public BookMarker patchBookMarker(BookMarker bookMarker, long bookMarkerId) {
        BookMarker findBookMarker = getBookMarker(bookMarkerId);

        Optional.ofNullable(bookMarker.getDate())
                .ifPresent(findBookMarker::setDate);

        Optional.ofNullable(bookMarker.getText())
                .ifPresent(findBookMarker::setText);

        bookMarkerRepository.save(findBookMarker);
        return bookMarker;
    }

    public BookMarker getBookMarker(long bookMarkerId) {
        Optional<BookMarker> optionalBookMarker = bookMarkerRepository.findById(bookMarkerId);
        BookMarker bookMarker = optionalBookMarker.orElseThrow(() -> new GlobalException(ApiCode.API_0000));
        return bookMarker;
    }

    public List<BookMarker> getBookMarkers() {
        List<BookMarker> bookMarkerList = bookMarkerRepository.findAll();
        return bookMarkerList;
    }

    public void deleteBookMarker(long bookMarkerId) {
        bookMarkerRepository.deleteById(bookMarkerId);
    }
}
