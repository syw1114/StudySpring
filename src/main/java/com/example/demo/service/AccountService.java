package com.example.demo.service;

import com.example.demo.dto.CheckoutRespDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserAddressReqDto;

public interface AccountService {
    public void duplicateEmail(SignUpDto signUpDto) throws Exception;
    public void register(SignUpDto signUpDto) throws Exception;
    public void address(UserAddressReqDto userAddressReqDto) throws Exception;
    public UserAddressReqDto getUserAddress(int userId) throws Exception;
    public CheckoutRespDto getPaymentProduct(int pdtDtlId) throws Exception;
}
