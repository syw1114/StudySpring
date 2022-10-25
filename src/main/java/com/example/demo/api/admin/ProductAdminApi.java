package com.example.demo.api.admin;

import com.example.demo.aop.annotation.LogAspect;
import com.example.demo.aop.annotation.ValidAspect;
import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.admin.ProductRegisterReqDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")

public class ProductAdminApi {

    @LogAspect
    @ValidAspect
    @PostMapping

    public ResponseEntity<?> registerProductMst(@Validated @RequestBody ProductRegisterReqDTO productRegisterReqDTO, BindingResult bindingResult) {

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", null));
    }
    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList(){

        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully", null));
    }
}
