package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//모델과 뷰를 반환받기위해. 컨트롤러를 사용함.

@Controller
public class AccountCtroller {

    @GetMapping("/account/Login")
    public String login(){
        return "Login";
    }

    @GetMapping("/account/SignUp")
    public String signUp(){
        return "SignUp";
    }
}
