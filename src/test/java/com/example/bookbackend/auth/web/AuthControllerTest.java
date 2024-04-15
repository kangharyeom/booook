package com.example.bookbackend.auth.web;

import com.example.bookbackend.auth.application.AuthService;
import com.example.bookbackend.auth.application.dto.SignInInfo;
import com.example.bookbackend.auth.web.dto.Tokens;
import com.example.bookbackend.common.jwt.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = AuthController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)}
)
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthService authService;

    @WithMockUser
    @DisplayName("로그인 시 access, refresh token을 쿠키에 담아 반환한다.")
    @Test
    void setUpCookiesWhenSignIn() throws Exception {
        // given
        SignInInfo signInInfo = new SignInInfo("abc@gmail.com", "password");
        String accessToken = "1234";
        String refreshToken = "123456";
        Tokens tokens = Tokens.of(accessToken, refreshToken);

        when(authService.signIn(any()))
                .thenReturn(tokens);

        // when & then
        mockMvc.perform(post("/auth/sign-in")
                        .content(objectMapper.writeValueAsString(signInInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(cookie().value("access_token", accessToken))
                .andExpect(cookie().value("refresh_token", refreshToken));
    }
}