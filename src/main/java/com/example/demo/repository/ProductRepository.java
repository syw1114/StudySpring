package com.example.demo.repository;

import com.example.demo.domain.CollectionsProduct;
import com.example.demo.domain.PaymentProduct;
import com.example.demo.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<CollectionsProduct> getProductList(Map<String,Object> map) throws Exception;
    public Product getProduct(int pdtId) throws Exception;

}
