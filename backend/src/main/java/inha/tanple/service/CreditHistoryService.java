package inha.tanple.service;

import inha.tanple.domain.CreditHistory;
import inha.tanple.domain.CreditMethod;
import inha.tanple.domain.Member;
import inha.tanple.domain.Wallet;
import inha.tanple.dto.CreditSummary;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.CreditHistoryRepository;
import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditHistoryService {

    private final CreditHistoryRepository creditHistoryRepository;
    private final MemberRepository memberRepository;

    public CreditSummary getCreditSummary(Long memberId) {
        // TODO:

        //일단 memberId로 회원을 조회 (없으면 에러 문구 출력)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        // history객체를 이용해서 리스트를 만들기 - memberId로 찾아서 링크
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        //값 초기화 해두고
        int totalCredits = 0;
        int pendingCredits = 0;


        for (CreditHistory creditHistory : creditHistories) {
            //total에는 credit과 balance? 값을 합한 총량 저장
            totalCredits += creditHistory.getCredit();
            totalCredits += creditHistory.getBalance();

            // pending 보류중인 크레딧은 어떤 양을 더해야할지 잘 모르겠음
            // 크레딧의 상태가 보류중임을 확인?

            pendingCredits += creditHistory.getCredit();
            pendingCredits += creditHistory.getBalance();

        }
        // totalCredits와 pendinCredits의 값을 반환
        return new CreditSummary(totalCredits, pendingCredits);
    }

    public List<CreditHistory> getCreditHistories(Long memberId) {
        // TODO:

        //멤버 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        //CreditHistory에 따라 리스트 생성 - memberId로 해당 데이터 찾아오기
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        // 해당하는 정보 전부 리턴하기?

        for (CreditHistory creditHistory : creditHistories) {
            Long historyId = creditHistory.getId();
            int historyCredit = creditHistory.getCredit();
            int historyBalance = creditHistory.getBalance();
            CreditMethod creditMethod = creditHistory.getCreditMethod();
            String detail = creditHistory.getDetail();

        }

        return creditHistories;


    }

    public List<CreditHistory> getPendingCreditHistories(Long memberId) {
        // TODO:

        //멤버 체크
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        //wallet 정보 가져오기
        Wallet wallet = member.getWallet();


        //정보로 리스트 생성
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        //
        for (CreditHistory creditHistory : creditHistories) {
        // 그냥 credithistory랑 차이점?
            // 처리중인 크레딧
            Long historyId = creditHistory.getId();
            int historyCredit = creditHistory.getCredit();
            int historyBalance = creditHistory.getBalance();
            CreditMethod creditMethod = creditHistory.getCreditMethod();
            String detail = creditHistory.getDetail();

        }

        // TODO::
        return creditHistories;
    }

}