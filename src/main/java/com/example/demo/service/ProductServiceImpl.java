package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.CheckoutRespDto;
import com.example.demo.dto.CollectionListRespDto;
import com.example.demo.dto.ProductRespDto;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception {
        List<CollectionListRespDto> productList = new ArrayList<CollectionListRespDto>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("category", category);
        map.put("index", (page - 1) * 9);

        productRepository.getProductList(map).forEach(collectionsProduct -> {
            productList.add(collectionsProduct.toDto());
        });

        return productList;
    }

    @Override
    public ProductRespDto getProduct(int pdtId) throws Exception {
        Product product = productRepository.getProduct(pdtId);

        if(product == null) {
            Map<String, String> errormap = new HashMap<String, String>();

            errormap.put("error" , "등록되지 않은 상품입니다.");
            throw new CustomValidationException("GetProduct Error",errormap);
        }


        List<Map<String, Object>> pdtSizes = new ArrayList<Map<String, Object>>();

        List<String> pdtImgs = new ArrayList<String>();


        product.getPdt_dtls().forEach(dtl -> {
            //맵완성
            Map<String,Object> pdtDtlIdAndSize = new HashMap<String, Object>();

            pdtDtlIdAndSize.put("pdtDtlId", dtl.getId());
            pdtDtlIdAndSize.put("sizeId", dtl.getSize_id());
            pdtDtlIdAndSize.put("sizeName", dtl.getSize_name());
            pdtDtlIdAndSize.put("pdtStock", dtl.getPdt_stock());

            pdtSizes.add(pdtDtlIdAndSize);
        });

        product.getPdt_imgs().forEach(img -> {
            pdtImgs.add(img.getSave_name());
        });


        ProductRespDto dto = ProductRespDto.builder()
                .pdtId(product.getId())
                .pdtName(product.getPdt_name())
                .pdtPrice(product.getPdt_price())
                .pdtImgs(pdtImgs)
                .pdtSizes(pdtSizes)
                .build();

        return  dto;
    }

}


