package com.example.bookbackend.token.application.dto;

import com.example.bookbackend.member.domain.Member;
import com.example.bookbackend.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TokenCreationInfo {

    private long memberId;
    private Role role;

    @Builder
    private TokenCreationInfo(long memberId, Role role) {
        this.memberId = memberId;
        this.role = role;
    }

    public static TokenCreationInfo of(Member member) {
        return new TokenCreationInfo(member.getId(), member.getRole());
    }
}
