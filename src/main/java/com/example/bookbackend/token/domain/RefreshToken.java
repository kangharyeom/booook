package com.example.bookbackend.token.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class RefreshToken {

    @Id
    private Long memberId;

    private String token;

    public RefreshToken(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public void updateNewToken(String newToken) {
        this.token = newToken;
    }
}
