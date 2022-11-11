package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionListRespDto {
    private int productId;
    private String productName;
    private int productPrice;
    private String mainImg;
    private int productTotalCount;
}
