package com.example.demo.domain;

import com.example.demo.dto.UserAddressReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddress {

    private int id;
    private String address1;
    private String address2;
    private String address3;

    public UserAddressReqDto toDto() {
        return UserAddressReqDto.builder()
                .userId(id)
                .address1(address1)
                .address2(address2)
                .address3(address3)
                .build();
    }
}
