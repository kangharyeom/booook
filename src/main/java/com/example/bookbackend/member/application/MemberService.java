package com.example.bookbackend.member.application;

import com.example.bookbackend.member.application.dto.MemberInfo;
import com.example.bookbackend.member.application.dto.SignUpInfo;
import com.example.bookbackend.member.domain.Member;
import com.example.bookbackend.member.exception.MemberException;
import com.example.bookbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.bookbackend.common.response.ApiCode.*;

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
            throw new MemberException(API_2000);
        }
    }

    public Member findMemberBy(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException("email :: " + email, API_2001));
    }

    public MemberInfo findMemberBy(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("memberId :: " + memberId, API_2001));

        return MemberInfo.of(member);
    }

    public void saveSocialMember(String email, String name, String tel) {
        if (memberRepository.existsByEmail(email)) {
            return;
        }

        Member member = Member.builder()
            .email(email)
            .name(name)
            .tel(tel)
            .build();

        memberRepository.save(member);
    }
}
