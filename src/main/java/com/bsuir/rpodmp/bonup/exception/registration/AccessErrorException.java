package com.bsuir.rpodmp.bonup.exception.registration;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class AccessErrorException extends BaseValidationException {

    public AccessErrorException(String lang) {
        super(lang);
        setKey("message.access.error");
    }

}
