package inha.tanple.dto.response;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import inha.tanple.domain.Product;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class ProductResponseDto {
     Long productBarcode; // 바코드

     String company; // 제조사/유통사

     String productName; // 제품명

     int price; // 판매가

     String businessRegistrationNumber; // 사업자등록번호

     int earnedCredit; // 적립 포인트 -> 적립 크레딧

     String certificationCategory; // 인증구분

     float earningRate; // 적립율

     LocalDateTime registerStartDate; // 시작일

     LocalDateTime registerEndDate; // 마감일

    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();

        //추가한 자료형에 필요한 get 함수를 작성하였습니다.
        this.productBarcode = product.getProductBarcode();
        this.company = product.getCompany();
        this.businessRegistrationNumber = product.getBusinessRegistrationNumber();
        this.earnedCredit = product.getEarnedCredit();
        this.certificationCategory = product.getCertificationCategory();
        this.earningRate = product.getEarningRate();
        this.registerStartDate = product.getRegisterStartDate();
        this.registerEndDate = product.getRegisterEndDate();
    }
}
