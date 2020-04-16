package com.bsuir.rpodmp.bonup.dto.converter.user;

import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.model.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthUserDtoToUserConverter implements Converter<AuthUserDto, User> {

    @Override
    public User convert(AuthUserDto authUserDto) {
        return User.builder()
                .email(authUserDto.getEmail())
                .password(authUserDto.getPassword())
                .verifyMail(false)
                .mailCode(null)
                .token(null)
                .build();
    }

}
