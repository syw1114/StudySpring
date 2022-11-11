package com.example.demo.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponseDto {
    private int id; //value에는 id가 들어가고
    private String name; //
}