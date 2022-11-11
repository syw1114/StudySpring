
package com.example.demo.api.admin;

import com.example.demo.aop.annotation.LogAspect;
import com.example.demo.aop.annotation.ValidAspect;
import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.admin.ProductImgReqDto;
import com.example.demo.dto.admin.ProductRegisterDtlReqDto;
import com.example.demo.dto.admin.ProductRegisterReqDto;
import com.example.demo.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(
            @Valid @RequestBody ProductRegisterReqDto productRegisterReqDto, BindingResult bindingResult) throws Exception {



//        Random random = new Random();

//        for(int i=0; i<100; i++){
//            productRegisterReqDto.setCategory(i/10 + 1);
//            productRegisterReqDto.setName(name+ (i+1));
//            productRegisterReqDto.setPrice((random.nextInt(10) +1) * 100000); //1부터 10까지 랜덤 * 10만원 -> 100만원까지
//            productManagementService.registerMst(productRegisterReqDto);
//
//        }

            int category= productRegisterReqDto.getCategory();
            productRegisterReqDto.setCategory(category);
            String name = productRegisterReqDto.getName();
            productRegisterReqDto.setName(name);
            int price = productRegisterReqDto.getPrice();
            productRegisterReqDto.setPrice(price);
            productManagementService.registerMst(productRegisterReqDto);


        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {

        return ResponseEntity.ok()
                .body(new CMRespDto<>("get successfully", productManagementService.getCategoryList()));
    }

    @GetMapping("/option/products/mst")
    public ResponseEntity<?> getProductList() throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>("get successful", productManagementService.getProductMstList()));
    }

    @GetMapping("/option/products/size/{productId}")
    public ResponseEntity<?> getSizeList(@PathVariable int productId) throws Exception {
        return ResponseEntity.ok()
                .body(new CMRespDto<>("get Successfully", productManagementService.getSizeList(productId)));
    }


    @PostMapping("/product/dtl")
    public ResponseEntity<?> registerDtl(@RequestBody ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception{

        productManagementService.checkDuplicatedSize(productRegisterDtlReqDto);
        productManagementService.registerDtl(productRegisterDtlReqDto);

        return ResponseEntity.ok().body(new CMRespDto<>("Register Successfully", true));

    }

    @LogAspect
    @PostMapping("/product/img")
    public ResponseEntity<?> registerImg(ProductImgReqDto productImgReqDto) throws Exception{
        productManagementService.registerImg(productImgReqDto);
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully",true));
    }



}
