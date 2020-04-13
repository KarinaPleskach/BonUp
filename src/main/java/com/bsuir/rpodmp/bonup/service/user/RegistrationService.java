package com.bsuir.rpodmp.bonup.service.user;

import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.exception.RoleNotFoundException;

public interface RegistrationService {
    void saveUser(AuthUserDto userDto);
}
