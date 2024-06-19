package inha.tanple.service;

import inha.tanple.domain.*;
import inha.tanple.dto.CreditSummary;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.CreditHistoryRepository;
import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (creditHistory.isPending()) {
                pendingCredits += creditHistory.getCredit();
                pendingCredits += creditHistory.getBalance();
            }
        }
        // totalCredits와 pendinCredits의 값을 반환
        return new CreditSummary(totalCredits, pendingCredits);
    }

    public List<CreditHistory> getCreditHistories(Long memberId) {
        // TODO:

        if (memberId < 0 || memberId > memberRepository.count()) {
            throw new ResourceNotFoundException("Member not found, memberId: " + memberId);
        }

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

//        for (CreditHistory creditHistory : creditHistories) {
//        // 그냥 credithistory랑 차이점?
//            // 처리중인 크레딧
//            Long historyId = creditHistory.getId();
//            int historyCredit = creditHistory.getCredit();
//            int historyBalance = creditHistory.getBalance();
//            CreditMethod creditMethod = creditHistory.getCreditMethod();
//            String detail = creditHistory.getDetail();
//        }
        // 보류중인 creditHistory 필터링하여 반환
        List<CreditHistory> pendingCreditHistories = creditHistories.stream()
                .filter(CreditHistory::isPending)
                .toList();

        // TODO::
        return pendingCreditHistories;
    }

    // 기간 내 조건에 따라서 물품 목록 확인
    public List<CreditHistory> getCreditHistoriesWithinPeriod(Long memberId, LocalDate startDate, LocalDate endDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        return creditHistoryRepository.findByMemberIdAndDateBetween(memberId, startDate, endDate);
    }

    // 적립 혹은 소비에 따라서 크레딧 가져오기
    public List<CreditHistory> getCreditHistoriesByType(Long memberId, CreditType creditType) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        // 주어진 멤버의 리스트 조회
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        List<CreditHistory> filteredCreditHistories = creditHistories.stream()
                .filter(creditHistory -> creditHistory.getCreditType() == creditType)
                .collect(Collectors.toList());

        return filteredCreditHistories;
    }

    // 년도와 달이 주어지면 해당 기간의 크레딧 정보 리턴
    public List<CreditHistory> getCreditHistoriesByMonth(Long memberId, YearMonth targetMonth) {
        // Member 조회 (없으면 예외 발생)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        // 해당 월의 CreditHistory 리스트 조회 및 반환
        LocalDate startOfMonth = targetMonth.atDay(1);
        LocalDate endOfMonth = targetMonth.atEndOfMonth();
        return creditHistoryRepository.findByMemberIdAndDateBetween(memberId, startOfMonth, endOfMonth);
    }
}