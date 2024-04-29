package inha.tanple.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditSummary {
    private int totalCredits;
    private int pendingCredits;

    public CreditSummary(int totalCredits, int pendingCredits) {
        this.totalCredits = totalCredits;
        this.pendingCredits = pendingCredits;
    }

    // getter, setter 생략
}