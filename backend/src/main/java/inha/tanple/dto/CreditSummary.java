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

    public CreditSummary() {
        this.totalCredits = 0;
        this.pendingCredits = 0;
    }

    // getter, setter 생략
}