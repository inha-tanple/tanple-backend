package inha.tanple.dto.response;


import inha.tanple.dto.CreditSummary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditSummaryResponseDto {
    private int totalCredits;
    private int pendingCredits;

    public CreditSummaryResponseDto(CreditSummary creditSummary) {
        this.totalCredits = creditSummary.getTotalCredits();
        this.pendingCredits = creditSummary.getPendingCredits();
    }

}