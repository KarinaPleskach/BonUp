package com.bsuir.rpodmp.bonup.controller;

import com.bsuir.rpodmp.bonup.dto.model.email_code.EmailCode;
import com.bsuir.rpodmp.bonup.dto.model.password.NewPassword;
import com.bsuir.rpodmp.bonup.dto.model.response.ResponseWithMessage;
import com.bsuir.rpodmp.bonup.dto.model.response.ResponseWithToken;
import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.dto.model.user.EmailUser;
import com.bsuir.rpodmp.bonup.service.translation.TranslationService;
import com.bsuir.rpodmp.bonup.service.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private TranslationService translationService;

    @PutMapping(value = "/{lang}/registration")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createNewUser(@PathVariable("lang") String lang, @RequestBody AuthUserDto user) {
        registrationService.createNewUser(user, lang);
        String message = translationService.getMessage("message.sendCode", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/verifyMail")
    @ResponseBody
    public ResponseEntity<ResponseWithToken> verifyMail(@PathVariable("lang") String lang, @RequestBody EmailCode emailCode) {
        registrationService.checkMailCode(emailCode, lang);
        String token = registrationService.generateNewToken(emailCode.getEmail(), lang);
        String message = translationService.getMessage("message.valid.email.code", lang);
        return new ResponseEntity<>(new ResponseWithToken(true, message, token), HttpStatus.OK);
    }

    @PostMapping("/{lang}/isUserExist")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> resetPassword(@PathVariable("lang") String lang, @RequestBody EmailUser emailUser) {
        registrationService.resendCodeAndNullToken(emailUser, lang);
        String message = translationService.getMessage("message.sendCode", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/newPassword")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> newPassword(@PathVariable("lang") String lang, @RequestBody NewPassword newPassword) {
        registrationService.newPassword(newPassword, lang);
        registrationService.generateNewTokenWithToken(newPassword.getToken(), lang);
        String message = translationService.getMessage("message.password.changed", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/login")
    @ResponseBody
    public ResponseEntity<ResponseWithToken> login(@PathVariable("lang") String lang, @RequestBody AuthUserDto authUserDto) {
        String token = registrationService.login(authUserDto, lang);
        String message = translationService.getMessage("message.correct.login", lang);
        return new ResponseEntity<>(new ResponseWithToken(true, message, token), HttpStatus.OK);
    }
}
