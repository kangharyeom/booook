package com.example.bookbackend.token.application;

import com.example.bookbackend.auth.web.dto.Tokens;
import com.example.bookbackend.common.jwt.JwtManager;
import com.example.bookbackend.member.domain.Role;
import com.example.bookbackend.token.domain.RefreshToken;
import com.example.bookbackend.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtManager jwtManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public Tokens createTokens(long memberId, Role role) {
        Instant now = Instant.now();
        String accessToken = jwtManager.createAccessToken(memberId, role, now);
        String refreshToken = jwtManager.createRefreshToken(now);

        saveRefreshToken(memberId, refreshToken);

        return Tokens.of(accessToken, refreshToken);
    }

    private void saveRefreshToken(Long memberId, String refreshToken) {
        refreshTokenRepository.findById(memberId)
                .ifPresentOrElse(
                        refresh -> refresh.updateNewToken(refreshToken),
                        () -> refreshTokenRepository.save(new RefreshToken(memberId, refreshToken))
                );
    }
}
