package com.example.demo.controller;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//모델과 뷰를 반환받기위해. 컨트롤러를 사용함.

@Controller
public class AccountCtroller {

    @GetMapping("/account/Login")
    public String login(Model model, @RequestParam @Nullable String email){
        model.addAttribute("email", email == null ? "" : email);
        return "Login";
    }

    @GetMapping("/account/SignUp")
    public String signUp(){
        return "SignUp";
    }
}
