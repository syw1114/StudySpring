package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductManagementController {

    @GetMapping("/product/register")
    public String loadProductRegister() {
        return "admin/admin_product_regist";
    }


    @GetMapping("/product/register/dtl")
    public String loadProductDtlRegister(){
        return "admin/admin_product_dtl_regist";
    }
}