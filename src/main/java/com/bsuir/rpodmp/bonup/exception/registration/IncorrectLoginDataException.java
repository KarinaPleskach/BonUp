package com.bsuir.rpodmp.bonup.exception.registration;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class IncorrectLoginDataException  extends BaseValidationException {

    public IncorrectLoginDataException(String lang) {
        super(lang);
        setKey("message.incorrect.login");
    }

}
