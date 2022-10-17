package com.example.demo.api;


import com.example.demo.aop.annotation.LogAspect;
import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.validation.ValidationSequence;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;
    @LogAspect
    @PostMapping("/SignUp")
    public ResponseEntity<?> SignUp(@Validated(ValidationSequence.class) @RequestBody SignUpDto signUpDto, BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(signUpDto);
        accountService.register(signUpDto);

        return ResponseEntity.created(null).body(new CMRespDto<>("회원가입 성공", signUpDto.getId()));
    }
}
