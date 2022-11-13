package com.example.demo.repository;

import com.example.demo.domain.PaymentProduct;
import com.example.demo.domain.User;
import com.example.demo.domain.UserAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public User findUserByEmail(String Id) throws Exception; // 이녀석 셀렉트 이메일을 가지고 찾아야함
    public int saveUser(User user) throws Exception;
    public int saveUserAddress(UserAddress userAddress) throws Exception;
    public UserAddress getUserAddress(int userId) throws Exception;
    public PaymentProduct getPaymentProduct(int pdtDtlId) throws Exception;

    public int updateUser(User user) throws Exception;
    public int deleteUser(User user) throws Exception;
}
