package com.example.bookbackend.book.web;

import com.example.bookbackend.auth.application.AuthService;
import com.example.bookbackend.book.application.BookService;
import com.example.bookbackend.book.domain.Book;
import com.example.bookbackend.book.domain.BookMapper;
import com.example.bookbackend.book.domain.BookRepository;
import com.example.bookbackend.book.presentation.BookController;
import com.example.bookbackend.common.jwt.JwtAuthenticationFilter;
import com.example.bookbackend.common.response.ApiCode;
import com.example.bookbackend.common.response.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = BookController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)}
)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;
    @MockBean
    private BookService bookService;
    @MockBean
    private BookMapper bookMapper;
    @MockBean
    private BookRepository bookRepository;

//    @WithMockUser
//    @DisplayName("책 등록 - 유효성 검사 실패")
//    @ParameterizedTest(name = "{index} => book={0}, response={1}")
//    @MethodSource("invalidBookArguments")
//    void shouldReturnBadRequestWhenInvalidBook(Book book, CommonResponse expectedResponse) throws Exception {
//        // given
//        String jsonResponse = objectMapper.writeValueAsString(expectedResponse);
//
//        // when & then
//        mockMvc.perform(post("/api/books")
//                        .content(objectMapper.writeValueAsString(book))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//                .andExpect(content().json(jsonResponse));
//    }

    @WithMockUser
    @DisplayName("책 등록 - 성공 케이스")
    @ParameterizedTest(name = "{index} => book={0}, response={1}")
    @MethodSource("validBookArguments")
    void shouldCreateBookSuccessfully(Book book, CommonResponse expectedResponse) throws Exception {
        // given
        String jsonResponse = objectMapper.writeValueAsString(expectedResponse);

        // when & then
        mockMvc.perform(post("/api/books")
                        .content(objectMapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().json(jsonResponse));
    }

    private static Stream<Arguments> invalidBookArguments() {
        return Stream.of(
                Arguments.of(
                        Book.builder()
                                .bookId(1L)
                                .title(null)
                                .startDate("2024-05-21")
                                .endDate("2024-05-21")
                                .summary("책의 줄거리 입니다.")
                                .text("내용 입니다.")
                                .imageUrl("www.naverBook.com")
                                .totalPageCount(0)
                                .goalPageCount(0)
                                .completedReading(false)
                                .build(),
                        CommonResponse.from(ApiCode.API_9000, Map.of(
                                "title", "책 제목은 필수 입력 값 입니다."
                        ))
                )
        );
    }

    private static Stream<Arguments> validBookArguments() {
        return Stream.of(
                Arguments.of(
                        Book.builder()
                                .bookId(1L)
                                .title("애국가")
                                .startDate("2024-05-21")
                                .endDate("2024-05-21")
                                .summary("대한민국의 국가이다.")
                                .text("애국가를 깊이 있게 공부해볼 사람들 모여라~")
                                .imageUrl("www.naverBook.com")
                                .totalPageCount(400)
                                .goalPageCount(20)
                                .completedReading(false)
                                .build(),
                        CommonResponse.from(ApiCode.API_0000, Map.of())
                )
        );
    }
}
