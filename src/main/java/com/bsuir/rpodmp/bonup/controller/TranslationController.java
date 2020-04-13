package com.bsuir.rpodmp.bonup.controller;

import com.bsuir.rpodmp.bonup.dto.model.translation.TranslationKeyDto;
import com.bsuir.rpodmp.bonup.service.translation.TranslationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PatchMapping("/activeLanguage")
    @ResponseBody
    public ResponseEntity<ResponseException> changeActiveLanguage(@RequestBody TranslationKeyDto translationKeyDto) {
        translationService.changeActiveLanguage(translationKeyDto);
        return new ResponseEntity<>(new ResponseException(translationKeyDto.getLanguageKey()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private static class ResponseException {
        private String message;
    }
}
