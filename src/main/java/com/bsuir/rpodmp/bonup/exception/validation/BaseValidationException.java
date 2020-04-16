package com.bsuir.rpodmp.bonup.exception.validation;

import com.bsuir.rpodmp.bonup.exception.BaseException;

public class BaseValidationException extends BaseException {

    public BaseValidationException(String lang) {
        super(lang);
        setKey("message.noValid");
    }

}
