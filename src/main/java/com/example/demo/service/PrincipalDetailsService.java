package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exception.CustomInternalServerErrorException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = null;

        try{
            user = accountRepository.findUserByEmail(email);
            log.info("{}", user);
        }catch (Exception e) {
            throw new CustomInternalServerErrorException("회원 정보 조회 오류");
        }if (user == null){
            throw new IllegalStateException("잘못 된 사용자 정보");
        }return new PrincipalDetails(user);
    }
}
