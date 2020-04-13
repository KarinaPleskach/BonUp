package com.bsuir.rpodmp.bonup.exception.validation.translation;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class NoSuchLanguageException extends BaseValidationException {
    public NoSuchLanguageException() {
        setKey("message.noLanguage");
    }
}
