package com.example.bookbackend.member.application.dto;

import com.example.bookbackend.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfo {

    private Long memberId;
    private String email;
    private String name;

    private MemberInfo(Long memberId, String email, String name) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
    }

    public static MemberInfo of(Member member) {
        return new MemberInfo(member.getId(), member.getEmail(), member.getName());
    }
}
