package inha.tanple.dto.response;

import inha.tanple.domain.Product;
import jakarta.persistence.Column;

import java.util.Date;

public class ProductResponseDto {
    Long barcode;
    String name;
    int price;

    String company;
    String productName;
    String taxNumber;
    int taxIncludedPrice;
    String category;
    float taxRate;
    Date startDate;
    Date endDate;

    public ProductResponseDto(Long barcode, String name, int price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }


    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();

        //추가한 자료형에 필요한 get함수를 작성하였습니다.
        this.barcode = product.getProductBarcode();
        this.company = product.getCompany();
        this.productName = product.getProductName();
        this.taxNumber = product.getTaxNumber();
        this.taxIncludedPrice = product.getTaxIncludedPrice();
        this.category = product.getCategory();
        this.taxRate = product.getTaxRate();
        this.startDate = product.getStartDate();
        this.endDate = product.getEndDate();


    }
}
