package com.bsuir.rpodmp.bonup.exception.handler;

import com.bsuir.rpodmp.bonup.dao.translation.LanguageKeyRepository;
import com.bsuir.rpodmp.bonup.dao.translation.LanguageRepository;
import com.bsuir.rpodmp.bonup.dao.translation.LanguageTranslationRepository;
import com.bsuir.rpodmp.bonup.exception.BaseException;
import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;
import com.bsuir.rpodmp.bonup.model.translation.LanguageTranslation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LanguageTranslationRepository languageTranslationRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private LanguageKeyRepository languageKeyRepository;

    @ExceptionHandler({BaseException.class})
    protected ResponseEntity<ResponseException> handleValidationException(BaseValidationException e) {
        LanguageTranslation translation = languageTranslationRepository.findByLanguageAndLanguageKey(
                languageRepository.findByLang(e.getLang())
                    .orElseThrow(BaseException::new),
                languageKeyRepository.findByKey(e.getKey())
                    .orElseThrow(BaseException::new))
                .orElseThrow(BaseException::new);
        return new ResponseEntity<>(new ResponseException(translation.getValue(), false), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class ResponseException {
        private String message;
        private Boolean isSuccess;
    }

}
