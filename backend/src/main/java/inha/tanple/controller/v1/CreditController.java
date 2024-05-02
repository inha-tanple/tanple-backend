package inha.tanple.controller.v1;

import inha.tanple.domain.CreditHistory;
import inha.tanple.service.CreditHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CreditController {

    private final CreditHistoryService creditHistoryService;

    @Operation(summary = "개인 크레딧 정보(크레딧, 거래내역, 처리중 내역)를 가져옵니다.")
    @GetMapping("/v1/credits")
    public List<CreditHistory> getCredits(
            @Nullable @RequestParam String category,
            @Nullable @RequestParam String startDate,
            @Nullable @RequestParam String endDate) {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1000L;

        // TODO: 기간 내 크레딧 정보 가져오기 로직 구현
        List<CreditHistory> credits = creditHistoryService.getCreditHistories(userId);

        return credits;
    }

    @Operation(summary = "개인 크레딧 정보를 캘린더 형식으로 가져옵니다.")
    @GetMapping("/v1/credits/calendar")
    public List<CreditHistory> getCreditsByCalendar() {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1000L;

        List<CreditHistory> credits = creditHistoryService.getCreditHistories(userId);
        return credits;
    }


}