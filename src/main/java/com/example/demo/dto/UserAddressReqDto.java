package com.example.demo.dto;


import com.example.demo.domain.Product;
import com.example.demo.domain.UserAddress;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressReqDto {

    private int userId;
    private String address1;
    private String address2;
    private String address3;

    public UserAddress toEntity() {
        return UserAddress.builder()
                .id(userId)
                .address1(address1)
                .address2(address2)
                .address3(address3)
                .build();
    }

}
