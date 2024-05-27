package com.example.bookbackend.book.web;

import com.example.bookbackend.auth.application.AuthService;
import com.example.bookbackend.book.application.BookMarkerService;
import com.example.bookbackend.book.domain.*;
import com.example.bookbackend.book.presentation.BookMarkerController;
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
        controllers = BookMarkerController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)}
)
public class BookMarkerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;
    @MockBean
    private BookMarkerService bookService;
    @MockBean
    private BookMarkerMapper bookMarkerMapper;
    @MockBean
    private BookMarkerRepository bookMarkerRepository;

    @WithMockUser
    @DisplayName("북마크 등록 - 성공 케이스")
    @ParameterizedTest(name = "{index} => book={0}, response={1}")
    @MethodSource("validBookMarkerArguments")
    void shouldCreateBookMarkerSuccessfully(BookMarker bookMarker, CommonResponse expectedResponse) throws Exception {
        // given
        String jsonResponse = objectMapper.writeValueAsString(expectedResponse);

        // when & then
        mockMvc.perform(post("/api/book/markers")
                        .content(objectMapper.writeValueAsString(bookMarker))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().json(jsonResponse));
    }

    private static Stream<Arguments> validBookMarkerArguments() {
        return Stream.of(
                Arguments.of(
                        BookMarker.builder()
                                .bookMarkerId(1L)
                                .date("2024-05-22")
                                .text("오늘의 글귀는 너무 감명깊었다.")
                                .build(),
                        CommonResponse.from(ApiCode.API_0000, Map.of())
                )
        );
    }
}
