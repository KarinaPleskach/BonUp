package com.bsuir.rpodmp.bonup.dto.model.user;

import lombok.Data;

@Data
public class AuthUserDto {
    private String email;
    private String password;
    private String name;
}
