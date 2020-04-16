package com.bsuir.rpodmp.bonup.service.user;

import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;

public interface RegistrationService {
    void createNewUser(AuthUserDto userDto, String lang);
}
