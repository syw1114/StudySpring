package com.example.demo.api;


import com.example.demo.aop.annotation.LogAspect;
import com.example.demo.dto.CMRespDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserAddressReqDto;
import com.example.demo.dto.validation.ValidationSequence;
import com.example.demo.securiry.PrincipalDetails;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @PostMapping("/signUp")
    public ResponseEntity<?> SignUp(@Validated(ValidationSequence.class) @RequestBody SignUpDto signUpDto, BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(signUpDto);
        accountService.register(signUpDto);

        return ResponseEntity.created(URI.create("/account/login")).body(new CMRespDto<>("회원가입 성공", signUpDto.getUsername()));
    }

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>("유저 정보 가져오기", principalDetails != null ? principalDetails.getUser() : null));
    }

    @LogAspect
    @PutMapping("/address")
    public ResponseEntity<?> saveAddress(@RequestBody UserAddressReqDto userAddressReqDto) throws Exception {

        System.out.println(userAddressReqDto);
        accountService.address(userAddressReqDto);

        return ResponseEntity.ok()
                .body(new CMRespDto<>("Register successfully", true));
    }



}
