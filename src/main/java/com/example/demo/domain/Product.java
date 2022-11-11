package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String pdt_name;
    private int category_id;
    private int pdt_price;
    private List<ProductDetail> pdt_dtls;
    private List<ProductImg> pdt_imgs;


}