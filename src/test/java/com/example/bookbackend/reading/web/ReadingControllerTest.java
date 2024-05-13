package com.example.bookbackend.reading.web;

import com.example.bookbackend.auth.application.AuthService;
import com.example.bookbackend.auth.application.dto.SignInInfo;
import com.example.bookbackend.common.jwt.JwtAuthenticationFilter;
import com.example.bookbackend.reading.application.dto.ReadingRequestDto;
import com.example.bookbackend.token.web.dto.Tokens;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ReadingController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)}
)
public class ReadingControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthService authService;

    @WithMockUser
    @DisplayName("마이페이지 접근시 읽은책에 관한 데이터 반환.")
    @Test
    void getMyPageData() throws Exception {
        // given
        SignInInfo signInInfo = new SignInInfo("abc@gmail.com", "password");
        String accessToken = "1234";
        String refreshToken = "123456";
        Tokens tokens = Tokens.of(accessToken, refreshToken);

        when(authService.signIn(any()))
                .thenReturn(tokens);



        ReadingRequestDto readingRequestDto = new ReadingRequestDto();
        readingRequestDto.setName("test");

        // when & then
        mockMvc.perform(post("/v1/api/reading/getReadingData")
                        .content(objectMapper.writeValueAsString(readingRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
        )
                .andExpect(status().isOk());
    }
}
