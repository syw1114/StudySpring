package com.example.demo.service.admin;

import com.example.demo.dto.admin.CategoryResponseDto;
import com.example.demo.dto.admin.ProductRegisterReqDTO;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDTO productRegisterReqDTO) throws Exception;
}
