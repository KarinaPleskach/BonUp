package com.bsuir.rpodmp.bonup.exception.validation.auth;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class NullRegistrationException extends BaseValidationException {
    public NullRegistrationException() {
        setKey("message.emptyMailOrPassword");
    }
}
