package com.bsuir.rpodmp.bonup.exception.registration;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class InvalidEmailCodeException extends BaseValidationException {

    public InvalidEmailCodeException(String lang) {
        super(lang);
        setKey("message.invalid.email.code");
    }

}
