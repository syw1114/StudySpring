package com.example.demo.controller;

import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.CheckoutRespDto;
import com.example.demo.securiry.PrincipalDetails;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CollectionsController {

    @GetMapping("/collections/{category}")
    public String loadCollections(@PathVariable String category) {

        return "product/product_collection";
    }

    @GetMapping("/product/{pdtId}")
    public String loadProductDetail(@PathVariable int pdtId) {
        return "product/product_detail";
    }

//    @GetMapping("/checkout")
//    public String loadPayment(Model model,
//                              @RequestParam int pdtId
//                              ) throws Exception{  //pdtDtlId = color - size 조합
//        CheckoutRespDto checkoutRespDto =  productService.getCheckoutProduct(pdtId);
//        model.addAttribute("data", checkoutRespDto);
//
//        return "product/product_order";
//    }


}
