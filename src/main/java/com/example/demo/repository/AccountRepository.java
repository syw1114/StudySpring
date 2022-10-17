package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public User findUserByEmail(String Id) throws Exception; // 이녀석 셀렉트 이메일을 가지고 찾아야함
    public int saveUser(User user) throws Exception;

}
