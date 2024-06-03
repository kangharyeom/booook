package com.example.bookbackend.auth.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SocialMemberInfo {

    @NotNull
    private String email;
    private String name;
    private String tel;

    public SocialMemberInfo(String email, String name, String tel) {
        this.email = email;
        this.name = name;
        this.tel = tel;
    }
}
