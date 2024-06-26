package inha.tanple.controller.v1;

import inha.tanple.domain.CreditHistory;
import inha.tanple.domain.CreditType;
import inha.tanple.dto.CreditSummary;
import inha.tanple.dto.response.CreditHistoryDto;
import inha.tanple.service.CreditHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Tag(name = "크레딧 조회", description = "크레딧 정보를 다양한 형태로 제공합니다.")
public class CreditController {

    private final CreditHistoryService creditHistoryService;

    @Operation(summary = "크레딧 정보 조회", description = "크레딧 현황, 거래 내역, 처리 내역 등 크레딧 정보를 가져옵니다.")
    @GetMapping("/v1/credits")
    public CreditSummary getCreditsSummary() {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        /**
         *    총 보유 크레딧
         *    저번 달 크레딧
         *    이번 달 크레딧
         */
        Long userId = 1000L;

        CreditSummary credits = creditHistoryService.getCreditSummary(userId);

        return credits;
    }

    @Operation(summary = "크레딧 정보 조회 - 캘린더 형식", description = "크레딧 정보를 캘린더 형식으로 가져옵니다.")
    @GetMapping("/v1/credits/{yearMonth}")
    public List<CreditHistoryDto> getCreditsByCalendar(
            @PathVariable String yearMonth) {

        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1000L;

        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, 6));

        YearMonth targetMonth = YearMonth.of(year, month);
        List<CreditHistory> credits = creditHistoryService.getCreditHistoriesByMonth(userId, targetMonth);

        return credits.stream()
                .map(creditHistory -> new CreditHistoryDto(creditHistory))
                .collect(Collectors.toList());
    }


    // ============= unused ===========

    @Operation(summary = "크레딧 적립/소비 조회", description = "크레딧 적립(CREDITED) 또는 소비(USED) 정보를 가져옵니다.")
    @GetMapping("/v1/credits/type")
    public List<CreditHistory> getCreditsByType(
            @RequestParam CreditType creditType) {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1000L;

        List<CreditHistory> credits = creditHistoryService.getCreditHistoriesByType(userId, creditType);
        return credits;
    }


}