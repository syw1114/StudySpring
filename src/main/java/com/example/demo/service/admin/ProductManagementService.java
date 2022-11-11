package com.example.demo.service.admin;

import com.example.demo.dto.admin.*;
import com.example.demo.dto.admin.ProductMstOptionRespDto;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

    public List<?> getSizeList(int productId) throws Exception;

    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;

    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception;

    public void checkDuplicatedSize(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception;
}
