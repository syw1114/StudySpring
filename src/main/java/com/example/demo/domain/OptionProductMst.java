package com.example.demo.domain;

import com.example.demo.dto.admin.ProductMstOptionRespDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OptionProductMst {
    private int pdt_id;
    private String category;
    private String pdt_name;

    public ProductMstOptionRespDto toDto() {
        return ProductMstOptionRespDto.builder()
                .pdtId(pdt_id)
                .category(category)
                .pdtName(pdt_name)
                .build();
    }
}
