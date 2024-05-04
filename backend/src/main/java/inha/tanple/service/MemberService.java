package inha.tanple.service;

import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public String authenticateWithGoogle(String idToken) {
        // Google OAuth 인증 로직 구현
        // 인증 정보를 바탕으로 User 엔티티 생성 또는 조회
        // 액세스 토큰 발급 및 반환
        return "access_token";
    }

    public String authenticateWithPhone(String phoneNumber, LocalDate birthDate) {
        // 전화번호와 생년월일로 인증 로직 구현
        // 인증 정보를 바탕으로 User 엔티티 생성 또는 조회
        // 액세스 토큰 발급 및 반환
        return "access_token";
    }
}