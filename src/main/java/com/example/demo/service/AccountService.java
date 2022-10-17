package com.example.demo.service;

import com.example.demo.dto.SignUpDto;

public interface AccountService {
    public void duplicateEmail(SignUpDto signUpDto) throws Exception;
    public void register(SignUpDto signUpDto) throws Exception;
}
