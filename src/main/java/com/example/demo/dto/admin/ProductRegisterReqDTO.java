package com.example.demo.dto.admin;

import com.example.demo.domain.Product;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductRegisterReqDto {

    private int category;
    private String name;
    @Min(value = 100, message = "최소 가격은 100원입니다.")
    private int price;


    public Product toEntity() {
        return Product.builder()
                .category_id((category))
                .pdt_name(name)
                .pdt_price(price)
                .build();
    }
}