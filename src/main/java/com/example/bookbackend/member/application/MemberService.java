package com.example.bookbackend.member.application;

import com.example.bookbackend.common.util.ApiCode;
import com.example.bookbackend.member.application.dto.SignUpInfo;
import com.example.bookbackend.member.domain.Member;
import com.example.bookbackend.member.exception.MemberException;
import com.example.bookbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.bookbackend.common.util.ApiCode.*;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveNewMember(SignUpInfo signUpInfo) {
        validateEmail(signUpInfo.getEmail());

        Member member = signUpInfo.toEntity(passwordEncoder);
        memberRepository.save(member);
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(API_1000);
        }
    }

    public Member findMemberBy(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException("email :: " + email, API_1001));
    }
}
