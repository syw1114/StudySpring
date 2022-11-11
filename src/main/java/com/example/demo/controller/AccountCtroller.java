package com.example.demo.controller;


import com.example.demo.dto.CheckoutRespDto;
import com.example.demo.dto.UserAddressReqDto;
import com.example.demo.securiry.PrincipalDetails;
import com.example.demo.service.AccountService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//모델과 뷰를 반환받기위해. 컨트롤러를 사용함.

@Controller
@RequiredArgsConstructor


public class AccountCtroller {

    private final ProductService productService;
    private final AccountService accountService;

    @GetMapping("/main")
    public String loadMain() {
        return "main";
    }

    @GetMapping("/account/login")
    public String login(Model model, @RequestParam @Nullable String username, @RequestParam @Nullable String error){
        model.addAttribute("username", username == null ? "" : username);
        model.addAttribute("error", error == null ? "" : error);
        return "/account/login";
    }

    @GetMapping("/account/signUp")
    public String signUp(){
        return "account/signUp";
    }

    @GetMapping("/account/order")
    public String loadPayment(Model model,
                             @RequestParam int pdtDtlId,
//                              userid가져와주세요.. acocunt repository service, impl , domain , 수정했음
                              @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
//
        CheckoutRespDto checkoutRespDto = accountService.getPaymentProduct(pdtDtlId);
        model.addAttribute("data", checkoutRespDto);
        model.addAttribute("user", principalDetails.getUser());
        //model.addAttribute("address", userAddressReqDto);

        return "account/pay_page";
    }
}
