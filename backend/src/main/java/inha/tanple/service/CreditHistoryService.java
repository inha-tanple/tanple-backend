package inha.tanple.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import inha.tanple.domain.*;
import inha.tanple.dto.CreditSummary;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.CreditHistoryRepository;
import inha.tanple.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        // memberId로 회원을 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        // history 객체를 이용해서 리스트를 만들기
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        //값 초기화 해두고
        int totalCredits = 0;
        int lastMonthCredits = 0;
        int thisMonthCredits = 0;

        YearMonth currentMonth = YearMonth.now();
        YearMonth lastMonth = currentMonth.minusMonths(1);

        for (CreditHistory creditHistory : creditHistories) {
            LocalDate createdDate = creditHistory.getCreatedDate().toLocalDate();
            YearMonth yearMonth = YearMonth.from(createdDate);
            int credits = creditHistory.getCredit();

            totalCredits += credits; // 전체 크레딧 누적

            if (yearMonth.equals(currentMonth)) {
                thisMonthCredits += credits;
            } else if (yearMonth.equals(lastMonth)) {
                lastMonthCredits += credits;
            }
        }
        return new CreditSummary(totalCredits, lastMonthCredits, thisMonthCredits);
    }

    public List<CreditHistory> getCreditHistories(Long memberId) {

        // 멤버 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        //CreditHistory 에 따라 리스트 생성 - memberId로 해당 데이터 찾아오기
        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        // 해당하는 정보 전부 리턴하기?

        for (CreditHistory creditHistory : creditHistories) {
            Long historyId = creditHistory.getId();
            int historyCredit = creditHistory.getCredit();
            int historyBalance = creditHistory.getBalance();
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

        List<CreditHistory> pendingCreditHistories = creditHistories.stream()
                .filter(CreditHistory::isPending)
                .toList();

        // TODO::
        return pendingCreditHistories;
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
        LocalDateTime startOfMonth = targetMonth.atDay(1).atStartOfDay();
        System.out.println("startOfMonth = " + startOfMonth);

        LocalDateTime endOfMonth = targetMonth.atEndOfMonth().atTime(23, 59, 59);
        System.out.println("endOfMonth = " + endOfMonth);

        return creditHistoryRepository.findByMemberIdAndDateBetween(memberId, startOfMonth, endOfMonth);
    }

}