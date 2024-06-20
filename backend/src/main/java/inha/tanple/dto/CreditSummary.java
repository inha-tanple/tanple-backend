package inha.tanple.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditSummary {
    /**
     * 총 보유 크레딧
     * 저번 달 크레딧
     * 이번 달 크레딧
     */

    private int totalCredits;
    private int lastMonthCredits;
    private int thisMonthCredits;

    public CreditSummary(int totalCredits, int lastMonthCredits, int thisMonthCredits) {
        this.totalCredits = totalCredits;
        this.lastMonthCredits = lastMonthCredits;
        this.thisMonthCredits = thisMonthCredits;

    }

    public CreditSummary() {
        this.totalCredits = 0;
        this.lastMonthCredits = 0;
        this.thisMonthCredits = 0;
    }

    // getter, setter 생략
}