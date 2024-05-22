package inha.tanple.service;

import inha.tanple.domain.Member;
import inha.tanple.domain.Wallet;
import inha.tanple.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Member join(Member member) {
        // 새로운 Wallet 인스턴스 생성
        Wallet wallet = new Wallet();
        wallet.setMember(member);
        member.setWallet(wallet);

        // Member 저장
        return memberRepository.save(member);
    }

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