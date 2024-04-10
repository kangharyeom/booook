package com.example.bookbackend.member.web;

import com.example.bookbackend.member.application.MemberService;
import com.example.bookbackend.member.application.dto.SignUpInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/sign-up")
    public void signUp(@RequestBody SignUpInfo signUpInfo) {
        memberService.saveNewMember(signUpInfo);
    }
}
