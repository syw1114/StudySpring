package com.example.demo.dto.admin;

import com.example.demo.domain.Product;

import javax.validation.constraints.Min;

public class ProductRegisterReqDTO {

    private int category;
    private String name;
    @Min(value = 100,message = "가격은 최소 100원입니다")
    private int price;
    private int pdt_count;
    private String simpleInfo;

    public Product toEntity() {
        return Product.builder()
                .category_id(category)
                .pdt_name(name)
                .pdt_price(price)
                .pdt_simple_info(simpleInfo)
                .build();
    }
}
