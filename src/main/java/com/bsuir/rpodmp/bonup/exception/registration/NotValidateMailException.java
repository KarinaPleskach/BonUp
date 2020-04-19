package com.bsuir.rpodmp.bonup.exception.registration;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class NotValidateMailException extends BaseValidationException {

    public NotValidateMailException(String lang) {
        super(lang);
        setKey("message.not.validate.mail");
    }

}
