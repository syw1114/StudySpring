package com.example.demo.service.admin;

import com.example.demo.dto.admin.CategoryResponseDto;
import com.example.demo.dto.admin.ProductRegisterReqDTO;
import com.example.demo.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductmanagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        return null;
    }

    @Override
    public void registerMst(ProductRegisterReqDTO productRegisterReqDTO) throws Exception {

    }
}