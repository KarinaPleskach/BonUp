package com.bsuir.rpodmp.bonup.controller;

import com.bsuir.rpodmp.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.rpodmp.bonup.dao.translation.LanguageRepository;
import com.bsuir.rpodmp.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.rpodmp.bonup.dto.model.response.ResponseWithMessage;
import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.exception.BaseException;
import com.bsuir.rpodmp.bonup.model.translation.LanguageTranslation;
import com.bsuir.rpodmp.bonup.service.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;

    @PutMapping(value = "/{lang}/registration")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createNewUser(@PathVariable("lang") String lang, @RequestBody AuthUserDto user) {
        registrationService.createNewUser(user, lang);
        LanguageTranslation translation = languageTranslationRepository.findByLanguageAndLanguageKey(
                    languageRepository.findByLang(lang)
                        .orElseThrow(BaseException::new),
                    languageKeyRepository.findByKey("message.successRegistration"))
                    .orElseThrow(BaseException::new);
        return new ResponseEntity<>(new ResponseException(translation.getValue()), HttpStatus.OK);
    }

//    @GetMapping("/login")
//    @ResponseBody
//    public ResponseEntity<ResponseException> login(@RequestParam(required = false) String error) {
//        LanguageTranslation translation;
//        if (Objects.isNull(error)) {
//            translation = languageTranslationRepository.findByLanguageAndLanguageKey(
//                    languageRepository.findByActiveTrue(),
//                    languageKeyRepository.findByKey("message.pleaseLogin"))
//                    .orElseThrow(BaseException::new);
//        } else {
//            translation = languageTranslationRepository.findByLanguageAndLanguageKey(
//                    languageRepository.findByActiveTrue(),
//                    languageKeyRepository.findByKey("message.failLogin"))
//                    .orElseThrow(BaseException::new);
//        }
//        return new ResponseEntity<>(new ResponseException(translation.getValue()), HttpStatus.FORBIDDEN);
//    }

}
