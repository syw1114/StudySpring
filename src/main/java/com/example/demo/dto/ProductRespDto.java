package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
@Builder

public class ProductRespDto {

    private int pdtId;
    private String pdtName;
    private int pdtPrice;
    private List<String> pdtImgs;
    private List<Map<String, Object>> pdtSizes;

}
