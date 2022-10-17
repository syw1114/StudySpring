package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.SignUpDto;
import com.example.demo.exception.CustomInternalServerErrorException;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public void duplicateEmail(SignUpDto signUpDto) throws Exception {

        User user = accountRepository.findUserByEmail(signUpDto.getId());

        if (user != null) {

            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소 입니다.");

            throw new CustomValidationException("Duplicate email", errorMap);
        }
        //회원가입 진행
    }

    @Override
    public void register(SignUpDto signUpDto) throws Exception{
        User user = signUpDto.toEntity();
        int result = accountRepository.saveUser(user);
        if(result == 0) {
            throw new CustomInternalServerErrorException("회원가입중 문제가 발생했습니다");
        }
    }
}