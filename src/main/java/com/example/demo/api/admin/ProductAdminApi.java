package com.example.demo.api.admin;

import com.example.demo.aop.annotation.LogAspect;
import com.example.demo.aop.annotation.ValidAspect;
import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.admin.ProductRegisterReqDTO;
import com.example.demo.repository.admin.ProductManagementRepository;
import com.example.demo.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("product")

    public ResponseEntity<?> registerProductMst(@Validated @RequestBody ProductRegisterReqDTO productRegisterReqDTO, BindingResult bindingResult) {

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", null));
    }
    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList()throws Exception{

        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully",  productManagementService.getCategoryList()));
    }
}
