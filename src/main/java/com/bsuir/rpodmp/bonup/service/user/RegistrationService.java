package com.bsuir.rpodmp.bonup.service.user;

import com.bsuir.rpodmp.bonup.dto.model.email_code.EmailCode;
import com.bsuir.rpodmp.bonup.dto.model.password.NewPassword;
import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.dto.model.user.EmailUser;

public interface RegistrationService {
    void createNewUser(AuthUserDto userDto, String lang);

    void checkMailCode(EmailCode emailCode, String lang);

    String generateNewToken(String email, String lang);

    void resendCodeAndNullToken(EmailUser emailUser, String lang);

    void newPassword(NewPassword newPassword, String lang);

    String generateNewTokenWithToken(String token, String lang);

    String login(AuthUserDto userDto, String lang);
}
