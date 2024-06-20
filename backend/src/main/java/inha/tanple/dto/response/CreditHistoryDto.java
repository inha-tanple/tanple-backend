package inha.tanple.dto.response;

import inha.tanple.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CreditHistoryDto {

    private Long id;


    private int credit;

    private int balance;

    private int plusACC;
    private String date;
    private String time;


    private CreditMethod creditMethod; // ["PURCHASE", "EXCHANGE", "DONATION"]

    private String detail; // 소금 1kg, PURCHASE, EXCHANGE, 세제 1L, ...

    private Long productBarcode; // 사진 업로드 일자, productBarcode 를 가져와 Dto 구성 예정


    private CreditType creditType; // ["적립", "소비"]

    public CreditHistoryDto(CreditHistory creditHistory) {
        this.id = creditHistory.getId();
        this.credit = creditHistory.getCredit();
        this.balance = creditHistory.getBalance();
        this.plusACC = creditHistory.getPlusACC();
        LocalDateTime createdDate = creditHistory.getCreatedDate();

        this.date = createdDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.time = createdDate.format(DateTimeFormatter.ofPattern("HH:mm"));

        this.creditMethod = creditHistory.getCreditMethod();
        this.detail = creditHistory.getDetail();

        PhotoUpload photoUpload = creditHistory.getPhotoUpload();
        if (photoUpload != null) {
            this.productBarcode = photoUpload.getProduct().getProductBarcode();
        }

        this.creditType = creditHistory.getCreditType();
    }
}
