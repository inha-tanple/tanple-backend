package inha.tanple.service;

import inha.tanple.domain.Member;
import inha.tanple.domain.Wallet;
import inha.tanple.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @PostConstruct
    private void init() {

        Member adminMember = new Member();
        adminMember.setBirthDate(LocalDateTime.of(2000, 1, 1, 0, 0));
        adminMember.setEmail("admin@tanple.com");
        adminMember.setPhoneNumber("012-0000-0000");
        adminMember.setPassword("password");
        this.join(adminMember);

        Member userMember = new Member();
        userMember.setBirthDate(LocalDateTime.of(2000, 2, 2, 0, 0));
        userMember.setEmail("user@tanple.com");
        userMember.setPhoneNumber("013-0000-0000");
        userMember.setPassword("password");
        this.join(userMember);
    }

    @Transactional
    public Member join(Member member) {
        // Wallet 인스턴스 생성
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