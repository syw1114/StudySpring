package com.example.demo.dto;

import com.example.demo.domain.User;
import com.example.demo.dto.validation.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class SignUpDto {

    @Size(min = 1, max = 16, message = "아이디는 16글자까지만 입력가능합니다." ,groups = ValidationGroups.SizeGroup.class)
    @NotBlank(message = "아이디는 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[a-z0-9]{1,16}$", message = "아이디는 영어와 숫자, 영어 소문자만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String id;

    @NotBlank(message = "비밀번호는 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    @Size(min=8, max=16, message = "비밀번호는 8자 부터 16자까지" ,groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-~!#$%^&*_]*$", message = "비밀번호는 특수기호, 영문, 숫자를 모두 포함해야합니다." , groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    @NotBlank(message = "비밀번호는 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    @Size(min=8, max=16, message = "비밀번호는 8자 부터 16자까지" ,groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-~!#$%^&*_]*$", message = "비밀번호는 특수기호, 영문, 숫자를 모두 포함해야합니다." , groups = ValidationGroups.PatternCheckGroup.class)
    private String reconfirm;

    //이름 : 한글자 이상, 세글자를 넘을 수는 없음. 무조건 한글이어야한다.
    @Size(min = 1, max = 4, message = "이름은 4글자까지만 입력가능합니다." ,groups = ValidationGroups.SizeGroup.class)
    @NotBlank(message = "이름은 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[가-힇]*$", message = "이름은 한글만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String name;

    @Size(min = 1, max = 13, message = "생년월일이 제대로 입력 되지않았습니다." ,groups = ValidationGroups.SizeGroup.class)
    @NotBlank(message = "생년월일은 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[0-9]*$", message = "생년월일 숫자만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String registerNumber;

    @NotBlank(message = "주소를 비워 둘 수 없습니다." , groups = ValidationGroups.NotBlankGroup.class)
    private String address;

    public User toEntity(){
        return User .builder()
                .id(id)
                .password(password)
                .name(name)
                .address(address)
                .build();
    }
}
