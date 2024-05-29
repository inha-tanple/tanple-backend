package inha.tanple.dto.response;

import inha.tanple.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long productBarcode; // 바코드

    private String company; // 제조사/유통사

    private String productName; // 제품명

    private int price; // 판매가

    private String businessRegistrationNumber; // 사업자등록번호

    private int earnedCredit; // 적립 포인트 -> 적립 크레딧

    private String certificationCategory; // 인증구분

    private float earningRate; // 적립율

    private String registerStartDate; // 시작일

    private String registerEndDate; // 마감일


    public ProductResponseDto(Product product) {
        this.productBarcode = product.getProductBarcode();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.company = product.getCompany();
        this.businessRegistrationNumber = product.getBusinessRegistrationNumber();
        this.earnedCredit = product.getEarnedCredit();
        this.certificationCategory = product.getCertificationCategory();
        this.earningRate = product.getEarningRate();
        this.registerStartDate = product.getRegisterStartDate();
        this.registerEndDate = product.getRegisterEndDate();
    }
}
