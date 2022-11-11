package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserAddress;
import com.example.demo.dto.CheckoutRespDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserAddressReqDto;
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

        User user = accountRepository.findUserByEmail(signUpDto.getUsername());

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



    @Override
    public void address(UserAddressReqDto userAddressReqDto) throws Exception {
        UserAddress userAddress = userAddressReqDto.toEntity();
        accountRepository.saveUserAddress(userAddress);
    }

    @Override
    public void updateUser (SignUpDto signUpDto) throws Exception {
        User user = signUpDto.toEntity();
        accountRepository.updateUser(user);
    }

//    @Override
//    public UserAddressReqDto getUserAddress(int userId) throws Exception {
//        return accountRepository.getUserAddress(userId).toDto();
//    }



    @Override
    public CheckoutRespDto getPaymentProduct(int pdtDtlId) throws Exception {
        return accountRepository.getPaymentProduct(pdtDtlId).toDto();
    }
}