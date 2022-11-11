package com.example.demo.repository.admin;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManagementRepository {
    public List<ProductCategory> getCategoryList() throws Exception;

    public List<OptionProductMst> getProductMstList() throws Exception;

    public int saveProductMst(Product product) throws Exception;

    public List<OptionProductSize> getSizeList(int productId) throws Exception;

    public int saveProductDtl(ProductDetail productDetail) throws Exception;

    public int saveProductImg(List<ProductImg> productImgs) throws Exception;

    public int findProductSize(ProductDetail productDetail) throws Exception;
}
