package inha.tanple.dto.response;

import inha.tanple.domain.Product;

public class ProductResponseDto {
    Long id;
    String name;
    int price;

    public ProductResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();

    }
}
